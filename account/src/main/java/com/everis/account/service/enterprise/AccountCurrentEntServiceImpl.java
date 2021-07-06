package com.everis.account.service.enterprise;

import com.everis.account.dao.entity.enterprise.AccountEnterpriseCurrent;
import com.everis.account.dao.repository.AccountCurrentEntRepository;
import com.everis.account.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class AccountCurrentEntServiceImpl implements AccountCurrentEntService{

    AccountCurrentEntRepository<AccountEnterpriseCurrent> repository;

    @Override
    public Flux<AccountEnterpriseCurrent> findAccountByRuc(String ruc) {
        return repository.findClientByRuc(ruc).switchIfEmpty(Mono.error(new NotFoundException("No se encontro")));
    }
}
