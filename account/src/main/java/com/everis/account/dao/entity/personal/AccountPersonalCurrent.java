package com.everis.account.dao.entity.personal;

import com.everis.account.dao.entity.Associated;
import com.everis.account.dao.entity.common.personal.ClientPersonal;
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
import java.util.UUID;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPersonalCurrent {

    @Id
    private UUID idAccount;
    private ClientPersonal client;
    private AccountCurrentProduct accountCurrentProduct;
    private String accountNumber;
    private String accountCardNumber;
    private List<Associated> associates;
    private BigDecimal amountAvailable;

    private Date creationDate;
    private Date lastUpdateDate;
}
