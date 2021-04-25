package br.com.fiap.pixvalidator.controller;

import br.com.fiap.pixvalidator.domain.PixRequest;
import br.com.fiap.pixvalidator.domain.PixResponse;
import br.com.fiap.pixvalidator.service.PixValidatorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PixValidatorController {

    private PixValidatorService pixValidatorService;

    public PixValidatorController(PixValidatorService pixValidatorService) {
        this.pixValidatorService = pixValidatorService;
    }

    @GetMapping(value = "/pix/{id}")
    public Mono<ResponseEntity<PixResponse>> GetById(@PathVariable @NotBlank String id)
    {
        return pixValidatorService.getPixById(id)
                .flatMap(pix -> Mono.just(ResponseEntity.ok(pix)));
    }

    @PostMapping(value = "/validate")
    public Mono<ResponseEntity<PixResponse>> validatePix(@RequestBody @Valid PixRequest pixRequest)
    {
        return pixValidatorService.validatePixRequest(pixRequest)
                .flatMap(pix -> Mono.just(ResponseEntity.ok(pix)));
    }

}
