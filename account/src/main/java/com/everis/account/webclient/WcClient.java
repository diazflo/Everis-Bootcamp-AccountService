package com.everis.account.webclient;

import com.everis.account.dao.entity.common.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;
@Slf4j
public class WcClient {
    private WebClient webClient;

    public WcClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Client> getClient(UUID id){
        return webClient.get()
                .uri(uriBuilder -> {
                    return uriBuilder
                            .path("/{id}")
                            .build(id);
                }).retrieve().bodyToMono(Client.class);
    }
}
