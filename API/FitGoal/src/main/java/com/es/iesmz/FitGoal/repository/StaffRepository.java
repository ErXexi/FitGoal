package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
    Set<Staff> findAll();
    Optional<Staff> findById(Long id);
    Optional<Staff> findByRole(String role);
}