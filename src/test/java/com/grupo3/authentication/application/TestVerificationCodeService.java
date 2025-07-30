package com.grupo3.authentication.application;

import com.grupo3.authentication.infrasctucture.service.security.VerificationCodeService;
import org.junit.jupiter.api.Test;
import  static org.junit.jupiter.api.Assertions.*;

public class TestVerificationCodeService {
    @Test
    public void testeGenerateCode(){
       VerificationCodeService verificationCodeService = new VerificationCodeService();
       Integer code = verificationCodeService.generateCode();
       assertNotNull(code);
    }

    @Test
    public void testFormatCode(){
        VerificationCodeService verificationCodeService = new VerificationCodeService();
        Integer code = verificationCodeService.generateCode();
        String formatedCode = String.format("%06d", code);

        assertNotNull(formatedCode);

        Integer size = formatedCode.length();
        assertEquals(6, size);
    }
}
