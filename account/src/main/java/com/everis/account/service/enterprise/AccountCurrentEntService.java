package com.everis.account.service.enterprise;

import reactor.core.publisher.Mono;

public interface AccountCurrentEntService<T> {

   public Mono<T> findAccountByRuc(String ruc);
}
