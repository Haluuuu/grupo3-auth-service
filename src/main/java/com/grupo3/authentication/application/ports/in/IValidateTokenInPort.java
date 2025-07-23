package com.grupo3.authentication.application.ports.in;

import com.grupo3.authentication.domain.models.TokenPayload;

public interface IValidateTokenInPort {
   TokenPayload validateToken(String token);
}
