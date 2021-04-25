package br.com.fiap.pixvalidator.service;

import br.com.fiap.pixvalidator.client.ChargeClient;
import br.com.fiap.pixvalidator.domain.PixContext;
import br.com.fiap.pixvalidator.domain.PixRequest;
import br.com.fiap.pixvalidator.domain.PixResponse;
import br.com.fiap.pixvalidator.repository.PixInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PixValidatorService {

    private PixInfoRepository pixValidatorRepository;

    private ChargeClient chargeClient;

    private Logger log = LoggerFactory.getLogger(PixValidatorService.class);

    public PixValidatorService(PixInfoRepository pixValidatorRepository, ChargeClient chargeClient) {
        this.pixValidatorRepository = pixValidatorRepository;
        this.chargeClient = chargeClient;
    }

    public Mono<PixResponse> getPixById(String id) {
        log.info("Get Pix by Id: " + id);

        var pixInfo = pixValidatorRepository.findById(id);

        if (pixInfo.isPresent()) {
            return Mono.just(pixInfo.get().toPixResponse());
        } else {
            return Mono.error(new RuntimeException("Pix not found"));
        }
    }

    public Mono<PixResponse> validatePixRequest(PixRequest pixRequest){

        log.info("Validating Pix Request...");

        PixContext context = PixContext.builder()
                .qrCodeId(pixRequest.getQrCodeId())
                .amount(pixRequest.getAmount())
                .status("VALIDATING")
                .build();

        return validatePixValues(context)
                .flatMap(pix -> savePixInfo(pix))
                .flatMap(pix -> validatePixInCharge(pix))
                .flatMap(pix -> updatePixInfoChargeAndStatus(pix))
                .flatMap(pix -> Mono.just(pix.toPixResponse()))
                .doOnSuccess(pix -> log.info("Pix Request Validated with Success!"))
                .doOnError(pix -> log.info("Error Validating Pix Request..."));
    }

    private Mono<PixContext> validatePixValues(PixContext context) {

        //Validações de negócio

        return Mono.just(context);
    }

    private Mono<PixContext> savePixInfo(PixContext context) {
        return Mono.just(pixValidatorRepository.save(context.toPixInfo(context)))
                .map(pixInfo -> context.addPixId(pixInfo));
    }

    private Mono<PixContext> validatePixInCharge(PixContext context) {
        return Mono.just(chargeClient.validatePix(context))
                .map(pixInfo -> {
                    context.setChargeId(pixInfo.toString());
                    context.setStatus("VALIDATED");
                    return context;
                });
    }

    private Mono<PixContext> updatePixInfoChargeAndStatus(PixContext context) {
        return Mono.just(pixValidatorRepository.save(context.toUpdatedPixInfo(context)))
                .map(pixInfo -> context);
    }
}
