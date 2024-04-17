package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Team;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

public interface TeamService {
    Set<Team> findAll();
    Optional<Team> findById(Long id);
    Set<Team> findByCity(String city);
    Set<Team> findByProvince(String province);
    Optional<Team> findByName(String name);
    Team findByPlayer(int id);
    Team addTeam(Team team);
    Team modifyTeam(Long id, Team newTeam);
    void deleteTeam(Long id);
}