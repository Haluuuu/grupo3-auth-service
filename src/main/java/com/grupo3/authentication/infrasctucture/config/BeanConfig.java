package com.grupo3.authentication.infrasctucture.config;

import com.grupo3.authentication.application.ports.out.IEncryptOutPort;
import com.grupo3.authentication.application.ports.out.ITokenOutPort;
import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.infrasctucture.repository.IUserRepository;
import com.grupo3.authentication.infrasctucture.repository.Impl.UserServiceImpl;
import com.grupo3.authentication.infrasctucture.security.EncryptService;
import com.grupo3.authentication.infrasctucture.security.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public IUserServiceOutPort userServiceOutPort(IUserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public ITokenOutPort tokenOutPort(JwtProperties jwtProperties) {
        return new TokenService(jwtProperties);
    }

    @Bean
    public IEncryptOutPort encryptOutPort(PasswordEncoder passwordEncoder) {
        return new EncryptService(passwordEncoder);
    }
}
