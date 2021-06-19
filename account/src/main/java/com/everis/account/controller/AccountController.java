package com.everis.account.controller;

import com.everis.account.dao.entity.Account;
import com.everis.account.dao.entity.Client;
import com.everis.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/bank")
public class AccountController<T> {

    @Autowired
    AccountService<Account> accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<T> createBankAccount(@RequestBody Account bankAccount){
        log.info("get id Client" + bankAccount.getClient().getIdClient());
        return accountService.createBankAccount(bankAccount).map(account ->  (T) account);
    }

    @GetMapping("/{id}")
    public Flux<Account> getAllClient(@PathVariable UUID id){
        return accountService.getAllClient(id);
    }
}
