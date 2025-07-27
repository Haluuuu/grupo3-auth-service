package com.grupo3.authentication.infrasctucture.security;

import com.grupo3.authentication.application.ports.out.IEncryptOutPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptService implements IEncryptOutPort {
    private final PasswordEncoder passwordEncoder;

    public EncryptService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public Boolean checkPassword(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
