package com.grupo3.authentication.application.ports.in;

import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;

public interface IVerificateUserInPort {
    User register(RegisterForm form);
    Integer generateCode(String email);
    String verificationUser(String email, Integer code);
}
