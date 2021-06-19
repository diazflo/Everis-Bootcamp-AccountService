package com.everis.account.service;

import com.everis.account.dao.entity.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountService<T extends Account> {

    Mono<T> createBankAccount(Account bankAccount);

    Flux<T> getAllClient(UUID id);
}
