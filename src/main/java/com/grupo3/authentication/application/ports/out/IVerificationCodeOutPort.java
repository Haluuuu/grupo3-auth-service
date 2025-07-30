package com.grupo3.authentication.application.ports.out;

public interface IVerificationCodeOutPort {
    Integer generateCode();
    void sendCode(String email, Integer code);
}
