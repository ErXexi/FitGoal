package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
    String attributes = " T.ID, T.NAME ";

    Set<Tag> findAll();

    Optional<Tag> findByName(String name);


    @Query(value = "SELECT" + attributes + "FROM TAG T INNER JOIN EXERCICE_TAG ET ON ET.ID_TAG = T.ID WHERE ET.ID_EXERCICE = :exerciceId", nativeQuery = true)
    Set<Tag> findByExercice(@Param("exerciceId") Long id);
}