package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Match;
import com.es.iesmz.FitGoal.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
    String attributes = " P.ID, P.NAME, P.SURNAMES, P.TEAM_ID, P.PHOTO, P.MINUTES, P.YELLOW_CARDS, P.RED_CARDS ";
    Set<Player> findAll();
    Optional<Player> findById(Long id);
    Set<Player> findByName(String name);
    Set<Player> findBySurname(String surname);

    @Query(value = "SELECT" + attributes + "FROM PLAYER P WHERE P.TEAM_ID = :id", nativeQuery = true)
    Set<Player> findByTeam(@Param("id") Long id);
}