package br.com.fiap.pixvalidator.controller;

import br.com.fiap.pixvalidator.domain.PixRequest;
import br.com.fiap.pixvalidator.domain.PixResponse;
import br.com.fiap.pixvalidator.service.PixValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PixValidatorController.class)
public class PixValidatorControllerTest {

    @InjectMocks
    private PixValidatorController pixValidatorController;

    @MockBean
    private PixValidatorService pixValidatorService;

    @Autowired
    private WebTestClient webClient;

    private static final String EXPECTED_ID = "972797a6-a52f-11eb-bcbc-0242ac130002";
    private static final String EXPECTED_QRCODE_ID = "QRCODE";
    private static final BigDecimal EXPECTED_AMOUNT = BigDecimal.valueOf(100);
    private static final String EXPECTED_CHARGE_ID = "CHARGE";
    private static final String EXPECTED_STATUS = "VALIDATED";

    @Test
    public void givenValidPixId_whenRunGetById_thenReturnValidPixResponse() {
        PixResponse pixResponse = PixResponse.builder()
                .id(EXPECTED_ID)
                .qrCodeId(EXPECTED_QRCODE_ID)
                .amount(EXPECTED_AMOUNT)
                .chargeId(EXPECTED_CHARGE_ID)
                .status(EXPECTED_STATUS)
                .build();

        Mockito.when(pixValidatorService.getPixById(EXPECTED_ID)).thenReturn(Mono.just(pixResponse));

        webClient.get()
                .uri("/pix/972797a6-a52f-11eb-bcbc-0242ac130002")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(EXPECTED_ID)
                .jsonPath("qrCodeId").isEqualTo(EXPECTED_QRCODE_ID)
                .jsonPath("amount").isEqualTo(EXPECTED_AMOUNT)
                .jsonPath("chargeId").isEqualTo(EXPECTED_CHARGE_ID)
                .jsonPath("status").isEqualTo(EXPECTED_STATUS);
    }

    @Test
    public void givenValidPixRequest_whenRunValidate_thenReturnValidPixResponse() {
        PixRequest pixRequest = PixRequest.builder()
                .qrCodeId(EXPECTED_QRCODE_ID)
                .amount(EXPECTED_AMOUNT)
                .build();

        PixResponse pixResponse = PixResponse.builder()
                .id(EXPECTED_ID)
                .qrCodeId(EXPECTED_QRCODE_ID)
                .amount(EXPECTED_AMOUNT)
                .chargeId(EXPECTED_CHARGE_ID)
                .status(EXPECTED_STATUS)
                .build();

        Mockito.when(pixValidatorService.validatePixRequest(pixRequest)).thenReturn(Mono.just(pixResponse));

        webClient.post()
                .uri("/validate")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(pixRequest))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(EXPECTED_ID)
                .jsonPath("qrCodeId").isEqualTo(EXPECTED_QRCODE_ID)
                .jsonPath("amount").isEqualTo(EXPECTED_AMOUNT)
                .jsonPath("chargeId").isEqualTo(EXPECTED_CHARGE_ID)
                .jsonPath("status").isEqualTo(EXPECTED_STATUS);
    }
}
