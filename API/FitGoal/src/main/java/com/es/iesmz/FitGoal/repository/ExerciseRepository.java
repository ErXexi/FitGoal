package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Exercise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}