package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {
}