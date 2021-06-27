package com.everis.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/account/home/")
public class HomeController {

    @GetMapping
    public String Home(){
        return "Servicio levantado";
    }
}
