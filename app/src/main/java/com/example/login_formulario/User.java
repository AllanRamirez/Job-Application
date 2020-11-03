package com.example.login_formulario;

public class User {

    public User(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol=rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    private String username, password, rol;
}
