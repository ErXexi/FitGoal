package com.es.iesmz.FitGoal.DTO.Helper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;
}
