package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Session;

import java.util.Optional;
import java.util.Set;

public interface SessionService {
    Set<Session> findAll();
    Optional<Session> findById(Long id);
    Set<Session> findByCreatorId(Long id);
    Session addSession(Session session);
    Session modifySession(Long id, Session newSession);
    void deleteSession(Long id);
}