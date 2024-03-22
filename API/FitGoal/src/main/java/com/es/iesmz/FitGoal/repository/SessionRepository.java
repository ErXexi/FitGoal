package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
    Set<Session> findAll();
    Optional<Session> findById(Long id);
    Set<Session> findByCreatorId(Long id);
}