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
    public Optional<Session> findByCreatorId(Long id) {
        return sessionRepository.findByCreatorId(id);
    }
}