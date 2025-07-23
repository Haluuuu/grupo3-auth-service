package com.grupo3.authentication.application.ports.out;

import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;

import java.util.Optional;

public interface IUserServiceOutPort {

    User registerUser(RegisterForm form);
    Optional<User> getUser(String username);
}
