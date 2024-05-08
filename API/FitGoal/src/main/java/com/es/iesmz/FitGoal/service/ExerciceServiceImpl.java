package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice;
import com.es.iesmz.FitGoal.DTO.Exercice.DtoExerciceOnSessionDelete;
import com.es.iesmz.FitGoal.DTO.Helper.DtoResponse;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.Session;
import com.es.iesmz.FitGoal.domain.SessionExercice;
import com.es.iesmz.FitGoal.repository.ExerciceRepository;
import com.es.iesmz.FitGoal.repository.SessionExerciceRepository;
import com.es.iesmz.FitGoal.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ExerciceServiceImpl implements ExerciceService {
    @Autowired
    private ExerciceRepository exerciceRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionExerciceRepository sessionExerciceRepository;

    @Override
    public Set<Exercice> findAll() {
        return exerciceRepository.findAll();
    }

    @Override
    public Optional<Exercice> findById(Long id) {
        return exerciceRepository.findById(id);
    }

    @Override
    public Set<Exercice> findByTag(String tag) {
        return exerciceRepository.findByTag(tag);
    }

    @Override
    public List<DtoExercice> findBySession(Long sessionId) {
        return exerciceRepository.findBySession(sessionId);
    }

    @Override
    public Exercice addExercice(Exercice exercice) {
        return exerciceRepository.save(exercice);
    }


    @Override
    public Exercice modifyExercice(Long id, Exercice newExercice) {
        Exercice ex = exerciceRepository.findById(id).orElseThrow();
        newExercice.setId(id);
        return exerciceRepository.save(newExercice);
    }

    @Override
    public Exercice getImageFirstExerciceFromSession(Long sessionId) {
        return exerciceRepository.getImageFirstExerciceFromSession(sessionId);
    }

    @Override
    public void addExerciceIntoSession(DtoSessionAddExercice data) {
        Exercice ex = exerciceRepository.findById(data.exerciceId).orElseThrow();
        Session s = sessionRepository.findById(data.sessionId).orElseThrow();
        SessionExercice sessionExercice = new SessionExercice(ex, s);
        sessionExerciceRepository.save(sessionExercice);
    }

    @Override
    @Transactional
    public void deleteExercice(Long id) {
        Exercice ex = exerciceRepository.findById(id).orElseThrow();
        exerciceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public DtoResponse deleteExerciceFromSelectedSession(DtoExerciceOnSessionDelete data) {
        DtoResponse response = new DtoResponse(false, "");
        try {
            exerciceRepository.deleteExerciceFromSession(data.getExerciceId(), data.getSessionId(), data.getAddedAt());
            response.setSuccess(true);
        } catch (DateTimeParseException ex) {
            ex.printStackTrace();
            response.setMessage("Error parsing date and time format. Please provide the date and time in the format 'yyyy-MM-dd HH:mm:ss'.");
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setMessage("An error occurred while deleting the exercise from the session. Please try again later.");
        }
        return response;
    }


    @Override
    @Transactional
    public void deleteExerciceFromSessions(Long exerciceId) {
        Exercice ex = exerciceRepository.findById(exerciceId).orElseThrow();
        sessionExerciceRepository.deleteExerciceFromSessions(exerciceId);
    }
}