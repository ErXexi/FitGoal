package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Exercise;
import com.es.iesmz.FitGoal.repository.ExerciseRepository;
import com.es.iesmz.FitGoal.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Override
    public Set<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public Set<Exercise> findByTag(String tag) {
        return exerciseRepository.findByTag(tag);
    }
}