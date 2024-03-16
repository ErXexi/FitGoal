package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
}