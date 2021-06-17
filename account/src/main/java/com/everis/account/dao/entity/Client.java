package com.everis.account.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Client {
    @Id
    private UUID idClient;
    private String name;
    private String typeClient;
    private String dni;
}
