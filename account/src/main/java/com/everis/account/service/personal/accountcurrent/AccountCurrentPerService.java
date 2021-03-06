package com.everis.account.service.personal.accountcurrent;

import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface AccountCurrentPerService<T> {

    Mono<T> createPersonalBankAccountCurrent(AccountPersonalCurrent bankAccount);

    Mono<T> getAccount(UUID id);

    Flux<T> findAccountByDni(String dni);

    Flux<T> findByAccountNumber(String accountNumber);
}
