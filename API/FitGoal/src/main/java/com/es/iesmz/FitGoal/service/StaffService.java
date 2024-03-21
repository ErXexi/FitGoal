package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Staff;

import java.util.Optional;
import java.util.Set;

public interface StaffService {
    Set<Staff> findAll();
    Optional<Staff> findById(Long id);
    Optional<Staff> findByRole(String role);
}