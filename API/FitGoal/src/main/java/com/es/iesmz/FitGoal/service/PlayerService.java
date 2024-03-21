package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Player;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PlayerService {
    Set<Player> findAll();
    Optional<Player> findById(Long id);
    Set<Player> findByName(String name);
    Set<Player> findBySurname(String surname);
    Set<Player> findByTeam(@Param("id") Long id);
}