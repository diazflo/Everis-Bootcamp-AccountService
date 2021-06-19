package com.everis.account.dao.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
@Builder
public class Client extends DataResponse {
    @Id
    private UUID idClient;
    private String typeClient;
    private String dni;
}
