package com.everis.account.service.personal.accountcurrent;

import com.everis.account.dao.entity.common.AccountCurrentProduct;
import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import com.everis.account.dao.repository.AccountCurrentRepository;
import com.everis.account.utils.NotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class AccountCurrentPerServiceImpl implements AccountCurrentPerService<AccountPersonalCurrent> {

    @Autowired
    private AccountCurrentRepository<AccountPersonalCurrent> repository;

    @Autowired
    private WebClient.Builder builder;

    @Value("${client.hostname.uri}")
    String portClient;
    @Value("${product.hostname.uri}")
    String portProduct;

    @Override
    public Mono createPersonalBankAccountCurrent(AccountPersonalCurrent personalCurrent) {
        log.info("paso 1 antes de realizar el llamado");
        String id = UUID.randomUUID().toString();
        return Mono.just(personalCurrent)
                .map(account -> {
                    account.setIdAccount(UUID.fromString(id));

                    Mono<ClientPersonal> clientMono = builder.build()
                            .get()
                            .uri(portClient + "client/personal/" + account.getClient().getIdClient())
                            .retrieve()
                            .bodyToMono(ClientPersonal.class);

                    log.info("uri client " + portClient + "client/personal/" + account.getClient().getIdClient());

                    clientMono.doOnNext(client -> {
                        account.setClient(ClientPersonal.builder()
                                .idClient(client.getIdClient())
                                .dni(client.getDni())
                                .typeClient(client.getTypeClient())
                                .build());
                        log.info("Client " + client.getIdClient());
                    });


                    Mono<AccountCurrentProduct> productMono = builder.build()
                            .get()
                            .uri(portProduct + "products/checking-account/" + account.getAccountCurrentProduct().getIdPCheckingAccount())
                            .retrieve()
                            .bodyToMono(AccountCurrentProduct.class);

                    log.info("uri product " + portProduct + "products/checking-account/" + account.getAccountCurrentProduct().getIdPCheckingAccount());

                    productMono.doOnNext(accountCurrentProduct -> {
                        account.setAccountCurrentProduct(AccountCurrentProduct.builder()
                                .idPCheckingAccount(accountCurrentProduct.getIdPCheckingAccount())
                                .limitMovements(accountCurrentProduct.getLimitMovements())
                                .productName(accountCurrentProduct.getProductName())
                                .maintenanceCost(accountCurrentProduct.getMaintenanceCost())
                                .currencyType(accountCurrentProduct.getCurrencyType())
                                .status(accountCurrentProduct.getStatus())
                                .typeProduct(accountCurrentProduct.getTypeProduct())
                                .build());
                        log.info("Product " + accountCurrentProduct.getIdPCheckingAccount());
                    });

                    account.setCreationDate(new Date());
                    account.setLastUpdateDate(new Date());

                    return account;
                }).flatMap(responseEntity -> repository.save(responseEntity));
    }

    @Override
    @HystrixCommand(fallbackMethod = "getAccountDefault")
    public Mono<AccountPersonalCurrent> getAccount(UUID id) {
        log.info("idRequest ");
        log.info("client request ");
        return repository.findById(id).switchIfEmpty(Mono.error(new NotFoundException("No se encontro cuenta")));
    }

    public Mono<AccountPersonalCurrent> getAccountDefault(UUID id, Throwable e) {
        e.printStackTrace();
        return Mono.error(e);
    }

    @Override
    public Flux<AccountPersonalCurrent> findAccountByDni(String dni) {
        return repository.findByClientDni(dni).switchIfEmpty(Mono.error(new NotFoundException("Error no se encuentra cuenta")));
    }

    @Override
    public Flux<AccountPersonalCurrent> findByAccountNumber(String accountNumber) {
        return repository.findByAccountNumber(accountNumber).switchIfEmpty(Mono.error(new NotFoundException("Error no se encuentra cuenta")));
    }




}
