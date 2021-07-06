package com.everis.account.controller;

import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import com.everis.account.service.personal.accountcurrent.AccountCurrentPerService;
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
@RequestMapping("/bank/account/current/personal")
public class AccountCurrentPersonalController<T> {

    @Autowired
    AccountCurrentPerService<AccountPersonalCurrent> accountCurrentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity> createBankAccount(@RequestBody AccountPersonalCurrent bankAccount){
        log.info("account current get id Client" + bankAccount.getClient().getIdClient());
        return accountCurrentService.createPersonalBankAccountCurrent(bankAccount).map(accountPersonalCurrent -> ResponseEntity.status(HttpStatus.CREATED).body(accountPersonalCurrent));
    }

    @GetMapping("/{id}")
    public Mono<AccountPersonalCurrent> getAllClient(@PathVariable UUID id){
        return accountCurrentService.getAccount(id);
    }

    @GetMapping("/dni/{dni}")
    public Flux<AccountPersonalCurrent> findAccounByDni(@PathVariable("dni") String dni){
        return accountCurrentService.findAccountByDni(dni);
    }

    @GetMapping("/accountnumber/{number}")
    public Flux<AccountPersonalCurrent> findByAccounNumber(@PathVariable("number") String accountNumber){
        return accountCurrentService.findByAccountNumber(accountNumber);
    }
}
