package com.everis.account.service.personal.accountcurrent;

import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import com.everis.account.dao.repository.AccountCurrentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class AccountCurrentServiceImpl implements AccountCurrentService<AccountPersonalCurrent> {

    @Autowired
    private AccountCurrentRepository<AccountPersonalCurrent> repository;

    @Autowired
    private WebClient.Builder builder;

    @Override
    public Mono createPersonalBankAccountCurrent(AccountPersonalCurrent personalCurrent) {
        log.info("paso 1 antes de realizar el llamado");
        String id = UUID.randomUUID().toString();
        return Mono.just(personalCurrent)
                .map(account -> {
                    account.setIdAccount(id);

                    Mono<ClientPersonal> clientMono = builder.build()
                            .get()
                            .uri("localhost:8083/client/personal/dni" + account.getClient().getIdClient())
                            .retrieve()
                            .bodyToMono(ClientPersonal.class);

                    clientMono.doOnNext(client -> {
                        account.setClient(client);
                        log.info("Client " + client.getIdClient());
                    });
                    /*Mono<AccountCurrentProduct> productMono = builder.build()
                            .get()
                            .uri("localhost:8082/product" + account.getAccountCurrentProduct().getIdProduct())
                            .retrieve()
                            .bodyToMono(AccountCurrentProduct.class);

                    productMono.doOnNext(accountCurrentProduct ->{
                        account.setAccountCurrentProduct(accountCurrentProduct);
                        log.info("Product " + accountCurrentProduct.getIdProduct());
                    });*/

                    account.setCreationDate(new Date());
                    account.setLastUpdateDate(new Date());

                    return account;
                }).flatMap(responseEntity -> repository.save(responseEntity));
    }

    @Override
    public Flux<AccountPersonalCurrent> getAccount(UUID id) {
        log.info("idRequest ");
        log.info("client request ");
        return repository.findAll();
    }
}
