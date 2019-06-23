package com.desafio.concrete.solutions.cadastroservice.infrastructure.helpers;

import com.desafio.concrete.solutions.cadastroservice.domain.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Classe que contêm metódos de ajuda para segurança.
 *
 * @author <a href="mailto:alexsros@gmail.com">Alex S. Rosa</a>
 * @since 18/12/2018 13:41:23
 */
public class SecurityHelper {

    private static final int TIME_EXPIRATION = 30000;

    public static String generateTokenJwt(UserEntity user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }
}
