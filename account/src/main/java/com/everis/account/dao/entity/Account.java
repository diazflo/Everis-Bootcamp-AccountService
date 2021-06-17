package com.everis.account.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Document
public class Account {

    @Id
    private UUID idAccount;
    private Client client;
    private Product product;
    private long accountNumber;
    private List<String> associates;
    private BigDecimal amountAvailable;

    private Date creationDate;
    private Date lastUpdateDate;
}
