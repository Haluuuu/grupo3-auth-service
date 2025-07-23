package com.grupo3.authentication.application.ports.out;

public interface IEncryptOutPort {

    String encryptPassword(String password);

    Boolean checkPassword(String password, String encryptedPassword);
}
