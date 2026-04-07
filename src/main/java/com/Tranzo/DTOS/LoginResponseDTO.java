package com.Tranzo.DTOS;

import com.Tranzo.Model.Enums.Role;

public class LoginResponseDTO {
    private String token;
    private String email;
    private Role role;

    public LoginResponseDTO(){}

    public LoginResponseDTO(String token, String email, Role role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
