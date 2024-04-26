package com.es.iesmz.FitGoal.DTO.Match;

import com.es.iesmz.FitGoal.DTO.Team.DtoTeam;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoMatch {
    @JsonFormat(pattern="dd-MM-yyyy")
    public LocalDate Date;
    public int Match_Day;
    public DtoTeam LocalTeam;
    public DtoTeam VisitingTeam;
}
