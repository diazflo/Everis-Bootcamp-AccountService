package com.everis.account.service.personal.saving;

import com.everis.account.dao.entity.common.AccountSavingProduct;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import com.everis.account.dao.entity.personal.AccountPersonalSaving;
import com.everis.account.dao.repository.AccountSavingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class AccountSavingServiceImpl implements AccountSavingService<AccountPersonalSaving> {

    @Autowired
    private AccountSavingRepository<AccountPersonalSaving> repository;
    @Autowired
    private WebClient.Builder builder;

    @Override
    public Mono<AccountPersonalSaving> createBankAccountSaving(AccountPersonalSaving accountPersonalSaving) {
        String id = UUID.randomUUID().toString();
        return Mono.just(accountPersonalSaving).map(
                saving -> {
                    saving.setIdAccountSaving(UUID.fromString(id));
                    Mono<ClientPersonal> clientMono = builder.build()
                            .get()
                            .uri("localhost:8083/client/personal/dni" + saving.getClient().getIdClient())
                            .retrieve()
                            .bodyToMono(ClientPersonal.class);

                    clientMono.doOnNext(client -> {
                        saving.setClient(client);
                        log.info("Client " + client.getIdClient());
                    });

                    Mono<AccountSavingProduct> productMono = builder.build()
                            .get()
                            .uri("localhost:8082/product" + saving.getProduct().getIdProduct())
                            .retrieve()
                            .bodyToMono(AccountSavingProduct.class);

                    productMono.doOnNext(accountCurrentProduct ->{
                        saving.setProduct(accountCurrentProduct);
                        log.info("Product " + accountCurrentProduct.getIdProduct());
                    });

                    saving.setCreationDate(new Date());
                    saving.setLastUpdateDate(new Date());

                    return saving;
                }
        ).flatMap(saving -> repository.save(saving));
    }
}
