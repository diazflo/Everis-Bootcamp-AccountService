package com.everis.account.dao.entity.common;

import com.everis.account.dao.entity.DataResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountCurrentProduct extends DataResponse {
    @Id
    private UUID idPCheckingAccount;
    private String productName;
    private BigDecimal maintenanceCost;
    private int limitMovements;
    private String currencyType;
    private String status;
    private String typeProduct;

}
