package com.es.iesmz.FitGoal.DTO.Match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoMatchEnd {
    public Map<Long, Integer> Goals;
    public int LocalTeamGoals;
    public int VisitingTeamGoals;
}
