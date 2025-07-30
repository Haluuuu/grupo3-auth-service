package com.grupo3.authentication.application.ports.out;

import java.util.Optional;

public interface ICacheVerificationCodeOutPort {

    void addCode(Integer code, String email);
    Optional<Integer> getCode(String email);

}
