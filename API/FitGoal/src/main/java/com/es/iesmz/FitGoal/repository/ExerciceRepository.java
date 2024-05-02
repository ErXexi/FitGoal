package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Exercice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.Optional;
@Repository
public interface ExerciceRepository extends CrudRepository<Exercice, Long> {
    String ATTRIBUTES= " E.ID, E.DESCRIPTION, E.IMAGE, E.NAME, E.VIDEO ";
    Set<Exercice> findAll();
    Optional<Exercice> findById(Long id);
    @Query(value = "SELECT" + ATTRIBUTES + "FROM EXERCICE E " +
            "JOIN EXERCICE_TAG ET ON E.ID = ET.ID_EXERCICE " +
            "JOIN TAG T ON ET.ID_TAG = T.ID " +
            "WHERE T.NAME = :tag", nativeQuery = true)
    Set<Exercice> findByTag(@Param("tag") String tag);

    @Query(value = "SELECT" + ATTRIBUTES + "FROM EXERCICE E INNER JOIN SESSION_EXERCICE SE ON E.ID = SE.EXERCICE_ID WHERE SE.SESSION_ID = :sessionId", nativeQuery = true)
    List<Exercice> findBySession(@Param("sessionId") Long id);

}