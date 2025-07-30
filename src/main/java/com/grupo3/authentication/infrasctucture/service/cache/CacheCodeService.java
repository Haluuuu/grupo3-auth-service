package com.grupo3.authentication.infrasctucture.service.cache;

import com.grupo3.authentication.application.ports.out.ICacheVerificationCodeOutPort;
import com.grupo3.authentication.application.ports.out.IVerificationCodeOutPort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
@Service
public class CacheCodeService implements ICacheVerificationCodeOutPort {

    private final RedisService redisService;
    public CacheCodeService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void addCode(Integer code, String email) {
        long expiration=Long.parseLong("600");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", code);

        redisService.set(email,hashMap,expiration);
    }

    @Override
    public Optional<Integer> getCode(String email) {

        HashMap<Object, Object> data = (HashMap<Object, Object>) redisService.get(email);
        boolean isExists= data.containsKey("code");
        return isExists ? Optional.of((Integer) data.get("code")) : Optional.empty();
    }
}
