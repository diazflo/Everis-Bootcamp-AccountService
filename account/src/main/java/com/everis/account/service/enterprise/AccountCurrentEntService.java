package com.everis.account.service.enterprise;


import reactor.core.publisher.Flux;

public interface AccountCurrentEntService<T> {

   public Flux<T> findAccountByRuc(String ruc);

   public Flux<T> findByAccountNumber(String ruc);
}
