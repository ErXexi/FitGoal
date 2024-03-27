package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Match;
import com.es.iesmz.FitGoal.repository.MatchRepository;
import com.es.iesmz.FitGoal.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchRepository matchRepository;
    @Override
    public Set<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> findById(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public Set<Match> findByDay(int day) {
        return matchRepository.findByDay(day);
    }

    @Override
    public Set<Match> findByLocalTeamId(int id) {
        return matchRepository.findByLocalTeamId(id);
    }

    @Override
    public Set<Match> findByVisitingTeamId(int id) {
        return matchRepository.findByVisitingTeamId(id);
    }
}