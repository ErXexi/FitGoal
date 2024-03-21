package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Boolean existsByEmail(String email);
    User findByEmail(String email);
    Set<User> findAll();
}
