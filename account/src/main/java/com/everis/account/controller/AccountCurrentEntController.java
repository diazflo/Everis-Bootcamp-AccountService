package com.everis.account.controller;

import com.everis.account.service.enterprise.AccountCurrentEntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/bank/account/current/enterprise")
public class AccountCurrentEntController {

    @Autowired
    AccountCurrentEntService service;

    @GetMapping("/ruc/{ruc}")
    public Mono<ResponseEntity> findAccounByDni(@PathVariable("ruc") String ruc){
        return service.findAccountByRuc(ruc).map(ResponseEntity::ok);
    }

}
