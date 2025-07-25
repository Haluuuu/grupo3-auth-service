package com.grupo3.authentication.application.ports.out;

import com.grupo3.authentication.domain.models.TokenPayload;

public interface ITokenOutPort {

    String generateToken(TokenPayload payload);

    TokenPayload decodeToken(String token);
}
