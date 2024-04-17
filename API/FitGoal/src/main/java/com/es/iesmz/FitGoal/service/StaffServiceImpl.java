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

    @Override
    public Staff addStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Staff modifyStaff(Long id, Staff newStaff) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        newStaff.setId(id);
        return staffRepository.save(newStaff);
    }

    @Override
    public void deleteStaff(Long id) {
        Staff staff = staffRepository.findById(id).orElseThrow();
        staffRepository.deleteById(id);
    }
}