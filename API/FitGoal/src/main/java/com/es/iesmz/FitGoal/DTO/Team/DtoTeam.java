package com.es.iesmz.FitGoal.DTO.Team;

import com.es.iesmz.FitGoal.DTO.Player.DtoPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoTeam {
    public String Name;
    public String Crest;
    public List<DtoPlayer> Players;
}
