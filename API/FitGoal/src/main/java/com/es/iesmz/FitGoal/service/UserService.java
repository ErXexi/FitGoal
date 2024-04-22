package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.User;

import java.util.Optional;
import java.util.Set;


public interface UserService {
    Set<User> findAll();
    Optional<User> findById(Long id);
    Boolean existsByEmail(String email);
    User findByEmail(String email);
}
