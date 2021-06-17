package com.everis.account.service;

import com.everis.account.dao.entity.Account;
import reactor.core.publisher.Mono;

public interface AccountService {

    Mono<Account> createBankAccount(Account bankAccount);
}
