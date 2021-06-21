package com.everis.account.controller;

import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
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
@RequestMapping("/bank/account/personal")
public class AccountCurrentPersonalController<T> {

    @Autowired
    AccountService<AccountPersonalCurrent> accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity> createBankAccount(@RequestBody AccountPersonalCurrent bankAccount){
        log.info("get id Client" + bankAccount.getClient().getIdClient());
        return accountService.createPersonalBankAccountCurrent(bankAccount).map(accountPersonalCurrent -> ResponseEntity.status(HttpStatus.CREATED).body(accountPersonalCurrent));
    }

    @GetMapping("/{id}")
    public Flux<AccountPersonalCurrent> getAllClient(@PathVariable UUID id){
        return accountService.getAccount(id);
    }
}
