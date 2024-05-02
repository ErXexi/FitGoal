package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.SessionExercice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SessionExerciceRepository extends CrudRepository<SessionExercice, Long> {
    String EX_ATTRIBUTES = " E.ID, E.DESCRIPTION, E.IMAGE, E.NAME, E.VIDEO ";
    String SE_ATTRIBUTES = " S.ID, S.NAME, S.URL, S.CREATOR_ID ";

    @Query(value = "SELECT" + EX_ATTRIBUTES + "FROM EXERCICE E INNER JOIN SESSION_EXERCICE SE ON E.ID = SE.EXERCICE_ID WHERE SE.SESSION_ID = :sessionId", nativeQuery = true)
    List<Exercice> getExercicesFromSessionId(@Param("sessionId") Long sessionId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Session_Exercice WHERE Exercice_Id=:exerciceId", nativeQuery = true)
    int deleteExerciceFromSessions(@Param("exerciceId") Long exerciceId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Session_Exercice WHERE Session_Id=:sessionId", nativeQuery = true)
    int deleteSession(@Param("sessionId") Long sessionId);
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM SESSION_EXERCICE WHERE ID=:id", nativeQuery = true)
    int deleteExerciceFromSelectedSession(@Param("id") Long id);
}
