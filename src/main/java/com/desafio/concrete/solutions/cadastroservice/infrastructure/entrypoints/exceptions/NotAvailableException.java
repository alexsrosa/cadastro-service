package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NotAvailableException extends RuntimeException {

    public NotAvailableException(String message) {
        super(message);
    }

    public NotAvailableException() {
        super("Endpoint não implementado!");
    }
}