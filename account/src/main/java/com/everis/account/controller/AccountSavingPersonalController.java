package com.everis.account.controller;

import com.everis.account.dao.entity.personal.AccountPersonalSaving;
import com.everis.account.service.personal.saving.AccountSavingPerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/bank/account/saving/personal")
public class AccountSavingPersonalController {

    @Autowired
    private AccountSavingPerServiceImpl service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity> createBankAccountSaving(@RequestBody AccountPersonalSaving saving){
        log.info("saving");
        return service.createBankAccountSaving(saving).map(account -> ResponseEntity.status(HttpStatus.CREATED).body(account));
    }

}
