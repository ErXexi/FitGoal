package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Exercice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.Optional;
@Repository
public interface ExerciceRepository extends CrudRepository<Exercice, Long> {
    String attributes = " E.ID, E.DESCRIPTION, E.IMAGE, E.NAME, E.VIDEO ";
    Set<Exercice> findAll();
    Optional<Exercice> findById(Long id);
    @Query(value = "SELECT" + attributes + "FROM EXERCICE E WHERE E.ID = " +
            "(SELECT ET.ID_EXERCICE FROM EXERCISE_TAG ET WHERE ET.ID_TAG = " +
            "(SELECT T.ID FROM TAG T WHERE T.NAME = :tag ))", nativeQuery = true)
    Set<Exercice> findByTag(@Param("tag") String tag);
}