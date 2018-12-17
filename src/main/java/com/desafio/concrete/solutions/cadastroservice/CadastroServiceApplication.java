package com.desafio.concrete.solutions.cadastroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
        (exclude = { SecurityAutoConfiguration.class })
public class CadastroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadastroServiceApplication.class, args);
    }

}

