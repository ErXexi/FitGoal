package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.repository.ExerciceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ExerciceServiceImpl implements ExerciceService {
    @Autowired
    private ExerciceRepository exerciceRepository;
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
}