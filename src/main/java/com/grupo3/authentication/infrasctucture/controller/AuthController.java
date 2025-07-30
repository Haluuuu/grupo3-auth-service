package com.grupo3.authentication.infrasctucture.controller;

import com.grupo3.authentication.application.service.LoginUserService;
import com.grupo3.authentication.application.service.ValidateTokenService;
import com.grupo3.authentication.domain.models.TokenPayload;
import com.grupo3.authentication.infrasctucture.model.dto.LoginResponse;
import com.grupo3.authentication.infrasctucture.model.dto.MessageResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUserService loginUserService;
    private final ValidateTokenService validateTokenService;

    public AuthController(
            LoginUserService loginUserService,
            ValidateTokenService validateTokenService) {
        this.loginUserService = loginUserService;
        this.validateTokenService = validateTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestParam String username,
            @RequestParam String password, HttpServletResponse response) {
        String token = loginUserService.loginUser(username, password);

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);

        LoginResponse data = new LoginResponse("Has iniciado sesión", token);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<MessageResponse> logout(@CookieValue(name = "token") String token, HttpServletResponse response) {

        MessageResponse msg = new MessageResponse();

        if(token == null || token.isEmpty()){
            msg.setMessage("no has iniciado sesión");
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

        Cookie cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setSecure(true);
        response.addCookie(cookie);

        msg.setMessage("Has cerrado sesión");
        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/validate")
    ResponseEntity<?> validate(@CookieValue(name = "token")  String token) {
        try {
            if(token.isEmpty()){
                MessageResponse data = new MessageResponse("No se puede validar la token");
                return new ResponseEntity<>(data, HttpStatus.NOT_ACCEPTABLE);
            }
            TokenPayload result = this.validateTokenService.validateToken(token);
            return new  ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            MessageResponse data = new MessageResponse("No se puede validar la token");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }
}
