package com.grupo3.authentication.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenPayload {
    private Integer id;
    private String username;
    private String email;
}
