package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.handler;

import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.EmailFoundException;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.InvalidSessionException;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UnauthorizedException;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler implements Handler {

    @ExceptionHandler({Exception.class, MethodNotAllowedException.class})
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return createReturn(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        return createReturn(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnauthorizedException.class, InvalidSessionException.class})
    public final ResponseEntity<Object> handleUnauthorizedException(Exception ex, WebRequest request) {
        return createReturn(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailFoundException.class)
    public final ResponseEntity<Object> handleEmailFoundException(Exception ex, WebRequest request) {
        return createReturn(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return createReturn(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
