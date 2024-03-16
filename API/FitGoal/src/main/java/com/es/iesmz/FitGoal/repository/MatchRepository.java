package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {
}