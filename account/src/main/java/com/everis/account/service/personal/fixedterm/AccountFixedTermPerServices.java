package com.everis.account.service.personal.fixedterm;


import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import reactor.core.publisher.Mono;

public interface AccountFixedTermPerServices<T> {
    Mono<T> createBankAccountFixedTerm(AccountPersonalFixedTerm accountPersonalFixedTerm);
}
