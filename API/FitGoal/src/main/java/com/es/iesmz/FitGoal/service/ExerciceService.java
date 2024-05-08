package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice;
import com.es.iesmz.FitGoal.DTO.Exercice.DtoExerciceOnSessionDelete;
import com.es.iesmz.FitGoal.DTO.Helper.DtoResponse;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.domain.Exercice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public interface ExerciceService {
    Set<Exercice> findAll();
    Optional<Exercice> findById(Long id);
    Set<Exercice> findByTag(String tag);
    List<DtoExercice> findBySession(Long sessionId);
    Exercice addExercice(Exercice exercice);
    Exercice modifyExercice(Long id, Exercice newExercice);
    Exercice getImageFirstExerciceFromSession(Long sessionId);
    void addExerciceIntoSession(DtoSessionAddExercice data);
    void deleteExercice(Long id);
    DtoResponse deleteExerciceFromSelectedSession(DtoExerciceOnSessionDelete data);
    void deleteExerciceFromSessions(Long exerciceId);
}
