package com.grupo3.authentication.infrasctucture.service.cache;

import com.grupo3.authentication.application.ports.out.ICacheUserOutPort;
import com.grupo3.authentication.domain.models.User;
import com.grupo3.authentication.domain.schemas.RegisterForm;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class CacheUserService implements ICacheUserOutPort {

    private final RedisService redisService;

    public CacheUserService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void addUser(RegisterForm registerForm) {
        Map<String, Object> data= new HashMap<>();
        data.put("email", registerForm.getEmail());
        data.put("username", registerForm.getUsername());
        data.put("lastName", registerForm.getLastName());
        data.put("password", registerForm.getPassword());
        data.put("name", registerForm.getName());
        redisService.set(registerForm.getEmail(),data,60*10 );
    }

    @Override
    public Optional<RegisterForm> getUser(String email) {
        Map<Object,Object> usermap = new HashMap<>();

        usermap= redisService.get(email);
        if(usermap==null){
            return Optional.empty();
        }
        RegisterForm user = new RegisterForm();
        user.setEmail(email);
        user.setUsername(usermap.get("username").toString());
        user.setLastName(usermap.get("lastName").toString());
        user.setPassword(usermap.get("password").toString());
        user.setName(usermap.get("name").toString());

        return Optional.of(user);
    }
}
