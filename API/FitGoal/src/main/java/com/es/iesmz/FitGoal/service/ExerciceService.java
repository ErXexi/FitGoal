package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Exercice;

import java.util.Optional;
import java.util.Set;

public interface ExerciceService {
    Set<Exercice> findAll();
    Optional<Exercice> findById(Long id);
    Set<Exercice> findByTag(String tag);
    Set<Exercice> findBySession(Long id);
    Exercice addExercice(Exercice exercice);
    Exercice modifyExercice(Long id, Exercice newExercice);
    void deleteExercice(Long id);
}
