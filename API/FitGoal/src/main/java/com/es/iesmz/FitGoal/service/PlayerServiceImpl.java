package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Player;
import com.es.iesmz.FitGoal.repository.PlayerRepository;
import com.es.iesmz.FitGoal.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Override
    public Set<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Set<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }

    @Override
    public Set<Player> findBySurname(String surname) {
        return playerRepository.findBySurname(surname);
    }

    @Override
    public Set<Player> findByTeam(Long id) {
        return playerRepository.findByTeam(id);
    }
}