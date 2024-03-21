package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
    String attributes = " M.ID, M.DATE, M.DAY, M.RESULT, M.TIME, M.LOCAL_TEAM_ID, M. VISITING_TEAM_ID ";
    Set<Match> findAll();
    Optional<Match> findById(Long id);
    Set<Match> findByDay(int day);
    Set<Match> findByLocalTeamId(int id);
    Set<Match> findByVisitingTeamId(int id);

}