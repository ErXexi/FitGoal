package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    String attributes = " T.ID, T.CITY, T.CREST, T.NAME, T.PROVINCE ";
    Set<Team> findAll();
    Optional<Team> findById(Long id);
    Set<Team> findByCity(String city);
    Set<Team> findByProvince(String province);
    Optional<Team> findByName(String name);
    @Query(value = "SELECT" + attributes + "FROM TEAM T INNER JOIN PLAYER P ON T.ID = P.TEAM_ID WHERE P.TEAM_ID = :id", nativeQuery = true)
    Team findByPlayer(@Param(value = "id")int id);

}