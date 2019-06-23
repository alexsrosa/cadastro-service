package com.desafio.concrete.solutions.cadastroservice.infrastructure.helpers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe que contêm metódos para criptografia de senha.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
public class CryptPasswordHelper {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String cryptPasswordEncoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean isPasswordInvalid(String requestPassword, String password) {
        return !bCryptPasswordEncoder.matches(requestPassword, password);
    }
}
