package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.Session;
import com.es.iesmz.FitGoal.repository.ExerciceRepository;
import com.es.iesmz.FitGoal.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Service
public class ExerciceServiceImpl implements ExerciceService {
    @Autowired
    private ExerciceRepository exerciceRepository;
    @Autowired
    private SessionRepository sessionRepository;

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
    public Set<Exercice> findBySession(Long id) {
        return exerciceRepository.findBySession(id);
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
    public void deleteExercice(Long id) {
        Exercice ex = exerciceRepository.findById(id).orElseThrow();
        exerciceRepository.deleteById(id);
    }
}