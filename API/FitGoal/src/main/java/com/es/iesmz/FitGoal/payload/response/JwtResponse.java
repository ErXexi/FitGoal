package com.es.iesmz.FitGoal.payload.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@ToString
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String surnames;
    private String email;
    private String password;
    private List<String> roles;

    public JwtResponse(String token, Long id, String email, String name, String surnames, String password, List<String> roles) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}