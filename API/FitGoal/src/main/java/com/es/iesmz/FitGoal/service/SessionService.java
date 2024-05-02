package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.DTO.Helper.DtoResponse;
import com.es.iesmz.FitGoal.DTO.Session.*;
import com.es.iesmz.FitGoal.domain.Session;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SessionService {
    Set<Session> findAll();
    Optional<Session> findById(Long id);
    Set<Session> findByCreatorId(Long id);
    DtoResponse addSession(DtoSession data, Long userId);
    DtoResponse addExerciceToSession(DtoSessionAddExercice data);
    DtoResponse removeExerciceToSession(Long sessionId, int index);
    DtoResponse modifySession(Long id, DtoSession data);

    DtoResponse deleteSession(Long id);
    DtoResponse deleteSessionRelationship(Long id);
}