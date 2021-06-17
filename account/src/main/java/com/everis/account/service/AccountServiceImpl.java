package com.everis.account.service;

import com.everis.account.dao.entity.Account;
import com.everis.account.dao.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Mono<Account> createBankAccount(Account bankAccount) {
        //TODO
        return null;
    }
}
