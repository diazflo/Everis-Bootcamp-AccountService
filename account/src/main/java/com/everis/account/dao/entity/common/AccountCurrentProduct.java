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
    private UUID idProduct;
    private String nameProduct;
    private BigDecimal amountMaintenance;
    private int movementLimit;
    private String currency;
    private String status;
    private String typeProduct;

}
