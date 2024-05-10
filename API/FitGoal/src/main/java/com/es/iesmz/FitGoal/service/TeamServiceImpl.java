package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Team;
import com.es.iesmz.FitGoal.repository.TeamRepository;
import com.es.iesmz.FitGoal.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Set<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Set<Team> findByCity(String city) {
        return teamRepository.findByCity(city);
    }

    @Override
    public Set<Team> findByProvince(String province) {
        return teamRepository.findByProvince(province);
    }

    @Override
    public Team findByUser(Long userId) {
        return teamRepository.findByUser(userId);
    }

    @Override
    public Optional<Team> findByName(String name) {
        return teamRepository.findByName(name);
    }

    @Override
    public Team findByPlayer(int id) {
        return teamRepository.findByPlayer(id);
    }

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team modifyTeam(Long id, Team newTeam) {
        Team team = teamRepository.findById(id).orElseThrow();
        newTeam.setId(id);
        return teamRepository.save(newTeam);
    }

    @Override
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow();
        teamRepository.deleteById(id);
    }
}