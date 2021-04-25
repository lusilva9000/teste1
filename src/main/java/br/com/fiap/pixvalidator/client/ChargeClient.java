package br.com.fiap.pixvalidator.client;

import br.com.fiap.pixvalidator.domain.PixContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChargeClient {

    private WebClient webClient = WebClient.create("http://localhost:8080");

    public Mono<String> validatePix(PixContext pixContext) {
        return webClient.post()
                .uri("https://webhook.site/62787998-43c2-4817-808c-a21b00cbbecc")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(pixContext), PixContext.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
