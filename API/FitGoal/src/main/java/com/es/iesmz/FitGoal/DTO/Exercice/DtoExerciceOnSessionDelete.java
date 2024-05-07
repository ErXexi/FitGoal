package com.es.iesmz.FitGoal.DTO.Exercice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class DtoExerciceOnSessionDelete {
    private Long exerciceId;
    private Long sessionId;
    private LocalDateTime addedAt;

    public DtoExerciceOnSessionDelete(Long exerciceId, Long sessionId, String addedAt) {
        this.exerciceId = exerciceId;
        this.sessionId = sessionId;
        this.addedAt = LocalDateTime.parse(addedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}