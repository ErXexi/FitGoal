package com.es.iesmz.FitGoal.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Set;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class UserSignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String surname;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> rol;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
