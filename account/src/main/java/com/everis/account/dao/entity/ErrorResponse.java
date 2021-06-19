package com.everis.account.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse extends DataResponse{
    private String message;
}
