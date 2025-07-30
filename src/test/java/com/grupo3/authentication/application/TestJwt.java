package com.grupo3.authentication.application;

import com.grupo3.authentication.domain.models.TokenPayload;
import com.grupo3.authentication.infrasctucture.config.JwtProperties;
import com.grupo3.authentication.infrasctucture.service.security.TokenService;
import org.junit.jupiter.api.Test;

import  static org.junit.jupiter.api.Assertions.*;

public class TestJwt {

    @Test
    void testJwtProperties(){
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("test_secret");
        jwtProperties.setExpiration("12345678");
        assertEquals("test_secret", jwtProperties.getSecret());
        assertEquals("12345678", jwtProperties.getExpiration());
    }

    @Test
    void testJwtGenerateToken(){
        // generar propiedades para jwt
        String secret = "test_secret_12345678_test_secret_12345678";
        String expiration = "12345678";
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret(secret);
        jwtProperties.setExpiration(expiration);

        // crear servicio
        TokenService tokenService = new TokenService(jwtProperties);

        // generar token
        TokenPayload payload = new TokenPayload(1, "testuser", "testemail@example.com");
        String token = tokenService.generateToken(payload);

        // validar token
        assertNotNull(token);
    }

    @Test
    void testJwtDecodeToken(){
        // configurar propiedades jwt
        String secret = "test_secret_12345678_test_secret_12345678";
        String expiration = "12345678";
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret(secret);
        jwtProperties.setExpiration(expiration);

        // crear servicio
        TokenService tokenService = new TokenService(jwtProperties);

        // generar token
        TokenPayload payload = new TokenPayload(1, "testuser", "testemail@example.com");
        String token = tokenService.generateToken(payload);

        // decodificar token
        TokenPayload decodedPayload = tokenService.decodeToken(token);

        // verificar si el payload del inicio y el payload decodificado es el mismo
        assertNotNull(decodedPayload);
        assertEquals(payload, decodedPayload);
    }
}
