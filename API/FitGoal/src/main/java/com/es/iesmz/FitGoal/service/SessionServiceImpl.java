package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.Session;
import com.es.iesmz.FitGoal.domain.User;
import com.es.iesmz.FitGoal.repository.ExerciceRepository;
import com.es.iesmz.FitGoal.repository.SessionRepository;
import com.es.iesmz.FitGoal.repository.UserRepository;
import com.es.iesmz.FitGoal.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ExerciceRepository exerciceRepository;

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
    public Session addSession(Session session, Long userId) {
        Session newSession = sessionRepository.save(session);

        Optional<User> optUser = userService.findById(userId);
        if(optUser.isPresent()){
            User user = optUser.get();
            newSession.setCreator(user);
        }else{
            throw new RuntimeException("User not found");
        }
        return sessionRepository.save(newSession);
    }

    @Override
    @Transactional
    public Session addExerciceToSession(Long exerciceId, Long sessionId) {
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);
        if(optionalSession.isEmpty()) throw new RuntimeException("Session not found");
        Session session = optionalSession.get();
        Optional<Exercice> optionalExercice = exerciceRepository.findById(exerciceId);
        if(optionalExercice.isPresent()) {
            Exercice exercice = optionalExercice.get();
            session.getExercices().add(exercice);
        }else{
            throw new RuntimeException("Exercice not found");
        }
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