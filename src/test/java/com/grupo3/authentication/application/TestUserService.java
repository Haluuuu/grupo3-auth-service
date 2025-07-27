package com.grupo3.authentication.application;

import com.grupo3.authentication.application.ports.out.IUserServiceOutPort;
import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import  static org.junit.jupiter.api.Assertions.*;

public class TestUserService {
    @Mock
    private IUserServiceOutPort userServiceOutPort;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userServiceOutPort = Mockito.mock(IUserServiceOutPort.class);
    }

    @Test
    void whenCreateUser_thenShouldReturnSavedUser(){
        // crear datos de prueba
        RegisterForm userToCreate = new RegisterForm("testuser", "testpassword", "test@example.com", "testname", "testlasname");
        User expectedUser = new User(null, "test", "test", "test@examaple", "test", "test");
        when(userServiceOutPort.registerUser(any(RegisterForm.class))).thenReturn(expectedUser);

        // Actuar
        User userCreated = userServiceOutPort.registerUser(userToCreate);

        // verificar
        assertNotNull(userCreated);
        assertEquals(expectedUser, userCreated);
        verify(userServiceOutPort, times(1)).registerUser(any(RegisterForm.class));
    }
}
