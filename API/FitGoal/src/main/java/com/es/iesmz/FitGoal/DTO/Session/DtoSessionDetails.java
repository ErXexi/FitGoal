package com.es.iesmz.FitGoal.DTO.Session;

import com.es.iesmz.FitGoal.domain.Exercice;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DtoSessionDetails extends DtoSession {
    public List<Exercice> exerciceList;
}
