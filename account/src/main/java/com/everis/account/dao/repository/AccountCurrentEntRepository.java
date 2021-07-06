package com.everis.account.dao.repository;

import com.everis.account.dao.entity.enterprise.AccountEnterpriseCurrent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface AccountCurrentEntRepository<T extends AccountEnterpriseCurrent> extends ReactiveMongoRepository<T, UUID> {
    public Flux<T> findClientByRuc(String ruc);
}
