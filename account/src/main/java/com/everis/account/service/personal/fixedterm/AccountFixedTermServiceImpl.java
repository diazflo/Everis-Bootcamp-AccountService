package com.everis.account.service.personal.fixedterm;

import com.everis.account.dao.entity.common.personal.ClientPersonal;
import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import com.everis.account.dao.repository.AccountFixedTermRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class AccountFixedTermServiceImpl implements AccountFixedTermServices<AccountPersonalFixedTerm> {


    @Autowired
    private AccountFixedTermRepository<AccountPersonalFixedTerm> repository;
    @Autowired
    private WebClient.Builder builder;


    @Override
    public Mono<AccountPersonalFixedTerm> createBankAccountFixedTerm(AccountPersonalFixedTerm accountPersonalFixedTerm) {
        String id = UUID.randomUUID().toString();
        return Mono.just(accountPersonalFixedTerm).map(
                fixedTerm -> {
                    fixedTerm.setIdAccount(UUID.fromString(id));
                    Mono<ClientPersonal> clientMono = builder.build()
                            .get()
                            .uri("localhost:8083/client/personal/dni" + fixedTerm.getClient().getIdClient())
                            .retrieve()
                            .bodyToMono(ClientPersonal.class);

                    clientMono.doOnNext(client -> {
                        fixedTerm.setClient(client);
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

                    fixedTerm.setCreationDate(new Date());
                    fixedTerm.setLastUpdateDate(new Date());

                    return fixedTerm;
                }
        ).flatMap(fixedTerm -> repository.save(fixedTerm));
    }
}
