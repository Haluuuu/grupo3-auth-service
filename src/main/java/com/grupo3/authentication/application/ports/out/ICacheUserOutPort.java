package com.grupo3.authentication.application.ports.out;

import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;

import java.util.Optional;

public interface ICacheUserOutPort {
    void addUser(RegisterForm user);

    Optional<RegisterForm> getUser(String email);
}
