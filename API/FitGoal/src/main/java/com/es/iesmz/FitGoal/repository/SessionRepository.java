package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Match;
import com.es.iesmz.FitGoal.domain.Session;
import com.es.iesmz.FitGoal.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    Set<Session> findAll();
    Optional<Session> findById(Long id);
    Optional<Session> findByCreatorId(Long id);
}