package com.grupo3.authentication.infrasctucture.service.security;

import com.grupo3.authentication.application.ports.out.IVerificationCodeOutPort;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
@Service
public class VerificationCodeService implements IVerificationCodeOutPort {

    @Override
    public Integer generateCode() {
       return ThreadLocalRandom.current().nextInt(0, 99999);
    }

    @Override
    public void sendCode(String email, Integer code) {
        String formatedCode = String.format("%06d", code);
        System.out.println(email + ":" + formatedCode);
    }
}
