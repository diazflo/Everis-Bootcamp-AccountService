package com.everis.account.service.personal.saving;

import com.everis.account.dao.entity.common.AccountSavingProduct;
import com.everis.account.dao.entity.personal.AccountPersonalSaving;
import reactor.core.publisher.Mono;

public interface AccountSavingService<T> {
    public Mono<T> createBankAccountSaving(AccountPersonalSaving accountPersonalSaving);
}
