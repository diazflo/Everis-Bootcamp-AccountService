package com.everis.account.controller;

import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import com.everis.account.service.personal.fixedterm.AccountFixedTermPerServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/bank/account/fxt/personal")
public class AccountFixedTermPersonalController {

    @Autowired
    private AccountFixedTermPerServices<AccountPersonalFixedTerm> services;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity> createBankAccountFixedTerm(@RequestBody AccountPersonalFixedTerm fixedTerm){
        log.info("fixed term");
        return services.createBankAccountFixedTerm(fixedTerm).map(fixed -> ResponseEntity.status(HttpStatus.CREATED).body(fixed));
    }
}
