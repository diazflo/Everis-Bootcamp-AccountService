package com.everis.account.service;

import com.everis.account.dao.entity.Account;
import com.everis.account.dao.entity.Client;
import com.everis.account.dao.repository.AccountRepository;
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
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository<Account> accountRepository;

    @Autowired
    private WebClient.Builder getClient;

    @Override
    public Mono createBankAccount(Account bankAccount) {
        log.info("paso 1 antes de realizar el llamado");
        String id = UUID.randomUUID().toString();
        return Mono.just(bankAccount)
                .map(account -> {
                    Mono<Client> clientMono = getClient.build()
                            .get()
                            .uri("localhost:8083/client/" + account.getClient().getIdClient())
                            .retrieve()
                            .bodyToMono(Client.class);

                    clientMono.map(client -> {
                        account.setIdAccount(id);
                        account.setClient(client);
                        account.setCreationDate(new Date());
                        account.setLastUpdateDate(new Date());

                        log.info("Client " + client.getIdClient());
                        return client;
                    });


                    return account;
                }).flatMap(responseEntity -> accountRepository.save(responseEntity));
    }

    @Override
    public Flux<Account> getAllClient(UUID id) {
        log.info("idRequest ");
        log.info("client request ");
        return accountRepository.findAll();
    }
}
