package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}