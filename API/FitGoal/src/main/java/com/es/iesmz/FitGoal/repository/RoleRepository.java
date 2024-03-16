package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.ERole;
import com.es.iesmz.FitGoal.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
