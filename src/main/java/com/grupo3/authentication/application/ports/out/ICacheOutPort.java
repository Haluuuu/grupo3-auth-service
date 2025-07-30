package com.grupo3.authentication.application.ports.out;

import java.util.Map;

public interface ICacheOutPort {
    public void set(String clave, Map<String, Object> datos, long tiempoEnSegundos);
    public Object get(String clave);
    public void delete(String clave);
}
