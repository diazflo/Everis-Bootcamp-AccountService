package com.everis.account.dao.repository;

import com.everis.account.dao.entity.personal.AccountPersonalSaving;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountSavingRepository<T extends AccountPersonalSaving> extends ReactiveMongoRepository<T, UUID> {
}
