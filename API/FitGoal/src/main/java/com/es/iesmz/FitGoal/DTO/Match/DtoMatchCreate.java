package com.es.iesmz.FitGoal.DTO.Match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoMatchCreate {
    public Long LocalTeamId;
    public Long VisitingTeamId;
    public LocalDate MatchDate;

}
