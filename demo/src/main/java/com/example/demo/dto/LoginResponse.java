package com.example.demo.dto;

public class LoginResponse {
    private Long id;
    private String email;

    public LoginResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

