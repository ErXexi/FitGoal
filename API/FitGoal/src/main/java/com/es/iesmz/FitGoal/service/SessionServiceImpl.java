package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Session;
import com.es.iesmz.FitGoal.repository.SessionRepository;
import com.es.iesmz.FitGoal.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Override
    public Set<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Session> findById(Long id) {
        return sessionRepository.findById(id);
    }

    @Override
    public Set<Session> findByCreatorId(Long id) {
        return sessionRepository.findByCreatorId(id);
    }

    @Override
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session modifySession(Long id, Session newSession) {
        Session session = sessionRepository.findById(id).orElseThrow();
        newSession.setId(id);
        return sessionRepository.save(newSession);
    }

    @Override
    public void deleteSession(Long id) {
        Session session = sessionRepository.findById(id).orElseThrow();
        sessionRepository.deleteById(id);
    }
}