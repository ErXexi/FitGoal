package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.Optional;
@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    String attributes = " E.ID, E.DESCRIPTION, E.IMAGE, E.NAME, E.VIDEO ";
    Set<Exercise> findAll();
    Optional<Exercise> findById(Long id);
    @Query(value = "SELECT" + attributes + "FROM EXERCISE E WHERE E.ID = " +
            "(SELECT ET.ID_EXERCISE FROM EXERCISE_TAG ET WHERE ET.ID_TAG = " +
            "(SELECT T.ID FROM TAG T WHERE T.NAME = :tag ))", nativeQuery = true)
    Set<Exercise> findByTag(@Param("tag") String tag);
}