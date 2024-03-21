package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Session;

import java.util.Optional;
import java.util.Set;

public interface SessionService {
    Set<Session> findAll();
    Set<Session> findById(Long id);
    Optional<Session> findByCreatorId(Long id);
}