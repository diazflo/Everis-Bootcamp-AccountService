package com.everis.account.service.personal.fixedterm;


import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import reactor.core.publisher.Mono;

public interface AccountFixedTermServices<T> {
    Mono<T> createBankAccountFixedTerm(AccountPersonalFixedTerm accountPersonalFixedTerm);
}
