package com.es.iesmz.FitGoal.service;

import com.es.iesmz.FitGoal.DTO.User.DtoUserStaff;
import com.es.iesmz.FitGoal.domain.Staff;
import com.es.iesmz.FitGoal.domain.Team;
import com.es.iesmz.FitGoal.domain.User;
import com.es.iesmz.FitGoal.repository.StaffRepository;
import com.es.iesmz.FitGoal.repository.TeamRepository;
import com.es.iesmz.FitGoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeamRepository teamRepository;

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
    public Staff addStaff(DtoUserStaff data) {
        Optional<Team> optionalTeam = teamRepository.findById(data.getTeamId());
        Optional<User> optionalUser = userRepository.findById(data.getUserId());
        String role = data.getRole();


        if(optionalTeam.isPresent() && optionalUser.isPresent()){
            Team team = optionalTeam.get();
            User user = optionalUser.get();

            Staff newStaff = new Staff(user.getName(), user.getSurname(), role, team);
            user.setTeam(team);
            return staffRepository.save(newStaff);
        }else{
            throw new RuntimeException("Invalid data");
        }
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