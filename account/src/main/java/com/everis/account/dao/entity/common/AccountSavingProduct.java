package com.everis.account.dao.entity.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountSavingProduct {
    @Id
    private UUID idProduct;
    private String name;
    private String limitMovements;
    private String currency;
    private String status;
}
