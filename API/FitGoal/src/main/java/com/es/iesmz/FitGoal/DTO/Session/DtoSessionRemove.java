package com.es.iesmz.FitGoal.DTO.Session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSessionRemove {
    @JsonProperty("sessionId")
    public Long SessionId;
}
