package com.es.iesmz.FitGoal.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoUserStaff {
    private Long userId;
    private Long teamId;
    private String role;

}
