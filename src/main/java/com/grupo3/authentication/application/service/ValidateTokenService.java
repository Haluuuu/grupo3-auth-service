package com.grupo3.authentication.application.service;

import com.grupo3.authentication.application.ports.in.IValidateTokenInPort;
import com.grupo3.authentication.application.ports.out.ITokenOutPort;
import com.grupo3.authentication.domain.models.TokenPayload;
import org.springframework.stereotype.Service;

@Service
public class ValidateTokenService implements IValidateTokenInPort {
    private final ITokenOutPort tokenOutPort;

    public ValidateTokenService(ITokenOutPort tokenOutPort) {
        this.tokenOutPort = tokenOutPort;
    }

    @Override
    public TokenPayload validateToken(String token) {
        return tokenOutPort.decodeToken(token);
    }
}
