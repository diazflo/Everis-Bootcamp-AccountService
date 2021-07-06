package com.everis.account.service.enterprise;

import com.everis.account.dao.entity.enterprise.AccountEnterpriseCurrent;
import com.everis.account.dao.repository.AccountCurrentEntRepository;
import com.everis.account.utils.NotFoundException;
import reactor.core.publisher.Mono;

public class AccountCurrentEntServiceImpl implements AccountCurrentEntService{

    AccountCurrentEntRepository<AccountEnterpriseCurrent> repository;

    @Override
    public Mono findAccountByRuc(String ruc) {
        return repository.findClientByRuc(ruc).switchIfEmpty(Mono.error(new NotFoundException("No se encontro")));
    }
}
