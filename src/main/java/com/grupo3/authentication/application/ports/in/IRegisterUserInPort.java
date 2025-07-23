package com.grupo3.authentication.application.ports.in;

import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;

public interface IRegisterUserInPort {
    User registerUser(RegisterForm form);
}
