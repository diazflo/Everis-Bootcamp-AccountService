package com.everis.account.dao.repository;

import com.everis.account.dao.entity.personal.AccountPersonalCurrent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository<T extends AccountPersonalCurrent> extends ReactiveMongoRepository<T, UUID> {
}
