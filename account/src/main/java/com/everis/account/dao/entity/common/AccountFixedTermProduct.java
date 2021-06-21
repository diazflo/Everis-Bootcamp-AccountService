package com.everis.account.dao.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class AccountFixedTermProduct {

    @Id
    private UUID idProduct;
    private String name;
    private Date dateMovement;
    private String currency;
    private String status;
    private String typeProduct;

}
