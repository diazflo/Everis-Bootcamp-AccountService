package com.everis.account.dao.repository;

import com.everis.account.dao.entity.enterprise.AccountEnterpriseCurrent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface AccountCurrentEntRepository<T extends AccountEnterpriseCurrent> extends ReactiveMongoRepository<T, UUID> {
}
