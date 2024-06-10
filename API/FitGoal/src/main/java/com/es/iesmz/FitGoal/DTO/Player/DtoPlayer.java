package com.es.iesmz.FitGoal.DTO.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoPlayer {
    public String Name;
    public String Surname;
    public String Position;
    public String photo;
    public Long teamId;
    public int number;
}
