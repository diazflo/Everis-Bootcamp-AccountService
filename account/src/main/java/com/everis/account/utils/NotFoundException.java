package com.everis.account.utils;

import lombok.Data;

@Data
public class NotFoundException extends Exception{
    private String message;

    public NotFoundException(String s) {
        message = s;
    }
}
