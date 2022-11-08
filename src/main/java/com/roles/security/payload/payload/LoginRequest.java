package com.roles.security.payload.payload;

public class LoginRequest {

    private String nombre;
    private String password;

    public LoginRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
