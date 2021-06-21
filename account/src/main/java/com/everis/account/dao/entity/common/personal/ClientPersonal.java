package com.everis.account.dao.entity.common.personal;

import com.everis.account.dao.entity.DataResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientPersonal extends DataResponse {
    @Id
    private UUID idClient;
    private String typeClient;
    private String dni;
}
