package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice;
import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
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

    @Query("SELECT new com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice(e.id, e.name, e.description, e.image, e.video, se.addedAt) FROM Exercice e JOIN e.sessionExercices se WHERE se.session.id = :sessionId ORDER BY se.addedAt")
    List<DtoExercice> findBySession(@Param("sessionId") Long sessionId);

    @Query(value = "SELECT"+ ATTRIBUTES + "FROM EXERCICE E INNER JOIN SESSION_EXERCICE SE ON SE.EXERCICE_ID = E.ID WHERE SE.SESSION_ID = :sessionId LIMIT 1", nativeQuery = true)
    Exercice getImageFirstExerciceFromSession(@Param("sessionId") Long sessionId);

    @Modifying
    @Query(value = "DELETE FROM SESSION_EXERCICE WHERE added_at = :addedAt AND EXERCICE_ID = :exerciceId AND SESSION_ID = :sessionId", nativeQuery = true)
    void deleteExerciceFromSession(@Param("exerciceId") Long exerciceId, @Param("sessionId") Long sessionId, @Param("addedAt") LocalDateTime addedAt);



}