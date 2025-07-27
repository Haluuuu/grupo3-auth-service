package com.grupo3.authentication.application.service;

import com.grupo3.authentication.application.ports.in.IRegisterUserInPort;
import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService implements IRegisterUserInPort {

    private final IUserServiceOutPort userService;

    public RegisterUserService(IUserServiceOutPort userServiceOutPort) {
        this.userService = userServiceOutPort;
    }

    @Override
    public User registerUser(RegisterForm form) {
       return userService.registerUser(form);
    }
}
