package com.es.iesmz.FitGoal.repository;

import com.es.iesmz.FitGoal.domain.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {
}