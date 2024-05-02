package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.domain.Exercice;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExerciceService {
    Set<Exercice> findAll();
    Optional<Exercice> findById(Long id);
    Set<Exercice> findByTag(String tag);
    List<Exercice> findBySession(Long id);
    Exercice addExercice(Exercice exercice);
    Exercice modifyExercice(Long id, Exercice newExercice);
    void addExerciceIntoSession(DtoSessionAddExercice data);
    void deleteExercice(Long id);
    void deleteExerciceFromSelectedSession(Long id);
    void deleteExerciceFromSessions(Long exerciceId);
}
