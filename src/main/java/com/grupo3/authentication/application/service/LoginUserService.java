package com.grupo3.authentication.application.service;

import com.grupo3.authentication.application.ports.in.ILoginUserInPort;
import com.grupo3.authentication.application.ports.out.IEncryptOutPort;
import com.grupo3.authentication.application.ports.out.ITokenOutPort;
import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.domain.models.TokenPayload;
import com.grupo3.authentication.domain.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserService implements ILoginUserInPort {

    private final IUserServiceOutPort userServiceOutPort;
    private final IEncryptOutPort encryptOutPort;
    private final ITokenOutPort tokenOutPort;


    public LoginUserService(IUserServiceOutPort userServiceOutPort, IEncryptOutPort encryptOutPort, ITokenOutPort tokenOutPort) {
        this.userServiceOutPort = userServiceOutPort;
        this.encryptOutPort = encryptOutPort;
        this.tokenOutPort = tokenOutPort;
    }


    @Override
    public String loginUser(String username, String password) {
        Optional<User> user = userServiceOutPort.getUser(username);

        if(user.isEmpty()) {
            throw new RuntimeException("User no encontrado");
        }

        if(!encryptOutPort.checkPassword(password,user.get().getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        Integer id = user.get().getId();
        String us3rname =  user.get().getUsername();
        String email = user.get().getEmail();
        TokenPayload tokenPayload = new TokenPayload();
        tokenPayload.setId(id);
        tokenPayload.setUsername(us3rname);
        tokenPayload.setEmail(email);

        return tokenOutPort.generateToken(tokenPayload);
    }
}
