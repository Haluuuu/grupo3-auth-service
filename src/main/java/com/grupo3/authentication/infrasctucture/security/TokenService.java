package com.grupo3.authentication.infrasctucture.security;

import com.grupo3.authentication.application.ports.out.ITokenOutPort;
import com.grupo3.authentication.domain.models.TokenPayload;

public class TokenService implements ITokenOutPort{

    @Override
    public String generateToken(TokenPayload payload) {
        return "";
    }

    @Override
    public TokenPayload decodeToken(String token) {
        return null;
    }
}
