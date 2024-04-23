package com.es.iesmz.FitGoal.DTO.Session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSessionResponse {
    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;
}
