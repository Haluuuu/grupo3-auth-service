package com.grupo3.authentication.infrasctucture.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/verify")
public class VerificationController {

    private final

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> register(@RequestBody HashMap<String, Object> payload) {
        return ResponseEntity.ok(new HashMap<>());
    }

}
