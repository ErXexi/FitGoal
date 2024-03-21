package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Team;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface TeamService {
    Set<Team> findAll();
    Optional<Team> findById(Long id);
    Set<Team> findByCity(String city);
    Set<Team> findByProvince(String province);
    Set<Team> findByName(String name);
    Team findByPlayer(int id);
}