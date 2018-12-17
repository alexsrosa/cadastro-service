package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Handler {

    default ResponseEntity<Object> createReturn(String message, HttpStatus httpStatus){
        return new ResponseEntity(new ExceptionResponse(message), httpStatus);
    }
}
