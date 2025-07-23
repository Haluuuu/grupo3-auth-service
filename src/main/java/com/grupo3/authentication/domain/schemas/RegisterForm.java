package com.grupo3.authentication.domain.schemas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    private String username;
    private String password;
    private String email;
    private String name;
    private String lastName;
}
