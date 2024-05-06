package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice;
import com.es.iesmz.FitGoal.domain.Exercice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Optional;
@Repository
public interface ExerciceRepository extends CrudRepository<Exercice, Long> {
    String ATTRIBUTES= " E.ID, E.DESCRIPTION, E.IMAGE, E.NAME, E.VIDEO, E.ADDED_AT ";
    Set<Exercice> findAll();
    Optional<Exercice> findById(Long id);
    @Query(value = "SELECT" + ATTRIBUTES + "FROM EXERCICE E " +
            "JOIN EXERCICE_TAG ET ON E.ID = ET.ID_EXERCICE " +
            "JOIN TAG T ON ET.ID_TAG = T.ID " +
            "WHERE T.NAME = :tag", nativeQuery = true)
    Set<Exercice> findByTag(@Param("tag") String tag);

    @Query(value = "SELECT new com.es.iesmz.FitGoal.dto.exercice.DtoExercice(e.name, e.description, e.video, e.image, se.addedAt) " +
            "FROM SessionExercice se JOIN se.exercice e WHERE se.session.id = :sessionId " +
            "ORDER BY se.addedAt", nativeQuery = false)
    List<DtoExercice> findBySession(@Param("sessionId") Long id);



    @Query(value = "DELETE FROM SESSION_EXERCICE SE WHERE SE.EXERCICE_ID = :exerciceId AND SE.SESSION_ID= :sessionId AND SE.ADDED_AT = :addedAt", nativeQuery = true)
    void deleteExerciceFromSession(@Param("exerciceId")Long exerciceId, @Param("sessionId") Long sessionId, @Param("addedAt")Date addedAt);

}