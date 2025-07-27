package com.grupo3.authentication.application;
import com.grupo3.authentication.application.ports.out.IEncryptOutPort;
import com.grupo3.authentication.application.ports.out.ITokenOutPort;
import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.application.service.LoginUserService;
import com.grupo3.authentication.domain.models.TokenPayload;
import com.grupo3.authentication.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import  static org.junit.jupiter.api.Assertions.*;

public class TestLoginUserService {
    private LoginUserService loginUserService;
    @Mock
    private IUserServiceOutPort userService;
    @Mock
    private IEncryptOutPort encryptService;
    @Mock
    private ITokenOutPort tokenService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userService = Mockito.mock(IUserServiceOutPort.class);
        encryptService = Mockito.mock(IEncryptOutPort.class);
        tokenService = Mockito.mock(ITokenOutPort.class);
        loginUserService = new LoginUserService(userService, encryptService, tokenService);
    }

    @Test
    void whenLogin_thenShouldReturnToken(){
        User expectedUser = new User(null, "test", "tespass", "testemail@example.com", "test", "test");
        String expectedToken = "exampleToken";
        String expectedHashPassword = "encryptedPassword";
        TokenPayload expectedPayload = new TokenPayload(
             expectedUser.getId(),
             expectedUser.getUsername(),
             expectedUser.getEmail()
        );

        String test_username = "admin";
        String test_pass = "testpass";

        // configurar comportamientos
        when(userService.getUser(any(String.class))).thenReturn(Optional.of(expectedUser));

        when(encryptService.encryptPassword(any(String.class))).thenReturn(expectedHashPassword);
        when(encryptService.checkPassword(any(String.class), any(String.class))).thenReturn(true);

        when(tokenService.generateToken(any(TokenPayload.class))).thenReturn(expectedToken);
        when(tokenService.decodeToken(any(String.class))).thenReturn(expectedPayload);

        when(loginUserService.loginUser(test_username, test_pass)).thenReturn(expectedToken);

        // generar datos de prueba
        Optional<User> saved_user = userService.getUser("admin");
        String token = loginUserService.loginUser(test_username, test_pass);


        // validar test
        assertNotNull(token);
        assertNotNull(saved_user);
        assertEquals(expectedToken, token);
        assertEquals(saved_user.get(), expectedUser);
    }
}
