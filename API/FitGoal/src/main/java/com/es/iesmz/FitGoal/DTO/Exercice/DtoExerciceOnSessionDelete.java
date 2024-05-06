package com.es.iesmz.FitGoal.DTO.Exercice;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class DtoExerciceOnSessionDelete {
    public Long exerciceId;
    public Long sessionId;
    public Date addedAt;
}
