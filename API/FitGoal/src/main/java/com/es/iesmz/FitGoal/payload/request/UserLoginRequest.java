package com.es.iesmz.FitGoal.payload.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserLoginRequest {
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}