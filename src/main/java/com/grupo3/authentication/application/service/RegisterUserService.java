package com.grupo3.authentication.application.service;

import com.grupo3.authentication.application.ports.in.IRegisterUserInPort;
import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;

public class RegisterUserService implements IRegisterUserInPort {

    private final IUserServiceOutPort userService;

    public RegisterUserService(IUserServiceOutPort userService) {
        this.userService = userService;
    }

    @Override
    public User registerUser(RegisterForm form) {
       return userService.registerUser(form);
    }
}
