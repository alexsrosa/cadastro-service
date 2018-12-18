package com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.controllers;

import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.exceptions.NotAvailableException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe com implementações do Endpoint de tratamento de erro.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
@RestController
public class MainController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public void error() {
        throw new NotAvailableException();
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
