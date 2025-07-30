package com.grupo3.authentication.infrasctucture.service.security;

import com.grupo3.authentication.application.ports.out.ITokenOutPort;
import com.grupo3.authentication.domain.models.TokenPayload;
import com.grupo3.authentication.infrasctucture.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService implements ITokenOutPort{

    private final Key secretKey;
    private final long expirationMillis;

    public TokenService(JwtProperties jwtProperties) {
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
        this.expirationMillis = Long.parseLong(jwtProperties.getExpiration());
    }

    @Override
    public String generateToken(TokenPayload payload) {
        return Jwts.builder().setSubject(payload.getId().toString())
                .claim("username", payload.getUsername())
                .claim("email", payload.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expirationMillis))
                .signWith(secretKey).compact();
    }
    @Override
    public TokenPayload decodeToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        TokenPayload payload = new TokenPayload();
        payload.setId(Integer.parseInt(claims.getSubject()));
        payload.setUsername(claims.get("username").toString());
        payload.setEmail(claims.get("email").toString());

        return payload;
    }
}
