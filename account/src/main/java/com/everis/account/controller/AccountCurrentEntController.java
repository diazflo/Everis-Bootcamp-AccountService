package com.everis.account.controller;

import com.everis.account.dao.entity.enterprise.AccountEnterpriseCurrent;
import com.everis.account.service.enterprise.AccountCurrentEntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/bank/account/current/enterprise")
public class AccountCurrentEntController {

    @Autowired
    AccountCurrentEntService<AccountEnterpriseCurrent> service;

    @GetMapping("/ruc/{ruc}")
    public Flux<ResponseEntity> findAccounByDni(@PathVariable("ruc") String ruc){
        return service.findAccountByRuc(ruc).map(ResponseEntity::ok);
    }

    @GetMapping("/accountNumber/{number}")
    public Flux<AccountEnterpriseCurrent> findByAccountNumber(@PathVariable("number") String number){
        return service.findByAccountNumber(number);
    }

}
