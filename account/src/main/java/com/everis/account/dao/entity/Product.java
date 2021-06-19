package com.everis.account.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Product extends DataResponse {
    @Id
    private UUID idProduct;
    private String typeProduct;

}
