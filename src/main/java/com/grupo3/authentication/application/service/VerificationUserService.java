package com.grupo3.authentication.application.service;

import com.grupo3.authentication.application.ports.in.IVerificateUserInPort;
import com.grupo3.authentication.application.ports.out.*;
import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationUserService implements IVerificateUserInPort {

    private final IUserServiceOutPort userService;
    private final ICacheUserOutPort cacheUserOutPort;
    private final ICacheVerificationCodeOutPort cacheVerificationCodeOutPort;
    private final IEncryptOutPort encryptOutPort;
    private final ITokenOutPort tokenOutPort;
    private final IVerificateUserInPort verificateUserInPort;

    public VerificationUserService(IUserServiceOutPort userService, ICacheUserOutPort cacheUserOutPort, ICacheVerificationCodeOutPort cacheVerificationCodeOutPort, IEncryptOutPort encryptOutPort, ITokenOutPort tokenOutPort, IVerificateUserInPort verificateUserInPort) {
        this.userService = userService;
        this.cacheUserOutPort = cacheUserOutPort;
        this.cacheVerificationCodeOutPort = cacheVerificationCodeOutPort;
        this.encryptOutPort = encryptOutPort;
        this.tokenOutPort = tokenOutPort;
        this.verificateUserInPort = verificateUserInPort;
    }

    /**
     * Registrar cuenta
     * @param form Formulario de registro de cuenta
     * @return instancia de tipo usuario
     * @throws RuntimeException en caso que la cuenta este en la base de datos o el cache
     */
    @Override
    public User register(RegisterForm form) {
        Optional<User> user= userService.getUser(form.getEmail());
        if(user.isPresent()&&(user.get().getEmail()
                .equals(form.getEmail())||user.get().getUsername().equals(form.getUsername()))) {
            throw new RuntimeException("El usuario ya existe en el sistema");
        }

        Optional<RegisterForm> userCache= cacheUserOutPort.getUser(form.getEmail());
        if(userCache.isPresent()) {
         throw new RuntimeException("La cuenta ya existe en el sistema, pero aun no ha sido verificada");
        }

        String hashPassword =  encryptOutPort.encryptPassword(form.getPassword());
        form.setPassword(hashPassword);

        User userResponse=new User();
        userResponse.setEmail(form.getEmail());
        userResponse.setUsername(form.getUsername());
        userResponse.setPassword(hashPassword);
        userResponse.setLastName(form.getLastName());
        userResponse.setName(form.getName());

        return userResponse;
    }

    @Override
    public Integer generateCode(String email) {
        return 0;
    }

    @Override
    public String verificationUser(String email, Integer code) {
        return "";
    }
}
