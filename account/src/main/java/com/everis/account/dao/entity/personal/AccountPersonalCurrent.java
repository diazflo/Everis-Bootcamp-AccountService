package com.everis.account.dao.entity.personal;

import com.everis.account.dao.entity.Associated;
import com.everis.account.dao.entity.common.Client;
import com.everis.account.dao.entity.common.AccountCurrentProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPersonalCurrent {

    @Id
    private String idAccount;
    private Client client;
    private AccountCurrentProduct accountCurrentProduct;
    private String accountNumber;
    private String accountCardNumber;
    private List<Associated> associates;
    private BigDecimal amountAvailable;

    private Date creationDate;
    private Date lastUpdateDate;
}
