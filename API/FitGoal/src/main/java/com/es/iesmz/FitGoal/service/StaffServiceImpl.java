package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.domain.Staff;
import com.es.iesmz.FitGoal.repository.StaffRepository;
import com.es.iesmz.FitGoal.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public Set<Staff> findAll() {
        return findAll();
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return findById(id);
    }

    @Override
    public Optional<Staff> findByRole(String role) {
        return findByRole(role);
    }
}