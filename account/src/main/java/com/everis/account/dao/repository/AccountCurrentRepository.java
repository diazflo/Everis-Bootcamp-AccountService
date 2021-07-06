package com.everis.account.dao.repository;

import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface AccountCurrentRepository<T extends AccountPersonalCurrent> extends ReactiveMongoRepository<T, UUID> {
    public Mono<T> findAccountByDni(String dni);
}
