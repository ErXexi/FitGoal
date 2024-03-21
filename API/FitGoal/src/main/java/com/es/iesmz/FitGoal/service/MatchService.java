package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Match;

import java.util.Optional;
import java.util.Set;

public interface MatchService {
    Set<Match> findAll();
    Optional<Match> findById(Long id);
    Set<Match> findByDay(int day);
    Set<Match> findByLocalTeamId(int id);
    Set<Match> findByVisitingTeamId(int id);
}