package com.grupo3.authentication.infrasctucture.service.cache;

import com.grupo3.authentication.application.ports.out.ICacheOutPort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;

@Service
public class RedisService implements ICacheOutPort {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void set(String clave, Map<String, Object> datos, long tiempoEnSegundos) {
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        hashOps.putAll(clave, datos); // Guarda el hash completo

        redisTemplate.expire(clave, Duration.ofSeconds(tiempoEnSegundos));
    }

    @Override
    public Map<Object, Object> get(String clave) {
        return redisTemplate.opsForHash().entries(clave);
    }

    @Override
    public void delete(String clave) {
        redisTemplate.delete(clave);
    }


}
