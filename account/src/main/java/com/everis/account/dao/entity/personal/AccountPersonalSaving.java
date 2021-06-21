package com.everis.account.dao.entity.personal;

import com.everis.account.dao.entity.Associated;
import com.everis.account.dao.entity.common.AccountSavingProduct;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountPersonalSaving {

    @Id
    private UUID idAccountSaving;
    private ClientPersonal client;
    private AccountSavingProduct product;
    private String accountNumberCard;
    private List<Associated> associated;
    private String accountNumber;
    private BigDecimal amountAvailable;
    private Date lastUpdateDate;
    private Date creationDate;
}
