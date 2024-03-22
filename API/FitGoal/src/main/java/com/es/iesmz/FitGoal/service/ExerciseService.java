package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Exercise;

import java.util.Optional;
import java.util.Set;

import java.util.Optional;
import java.util.Set;

public interface ExerciseService {
    Set<Exercise> findAll();
    Exercise findById(Long id);
    Set<Exercise> findByTag(String tag);
}
