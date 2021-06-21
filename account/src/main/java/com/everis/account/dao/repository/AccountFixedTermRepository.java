package com.everis.account.dao.repository;

import com.everis.account.dao.entity.personal.AccountPersonalFixedTerm;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountFixedTermRepository<T extends AccountPersonalFixedTerm> extends ReactiveMongoRepository<T, UUID> {

}
