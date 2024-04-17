package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.domain.*;
import com.es.iesmz.FitGoal.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//http://localhost:8080/api/auth/signup?username=Pepe&email=pepe@gmail.com&password=1234&role=admin
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StaffController {
    @Autowired
    StaffService staffService;
    @Operation(summary = "Get all Staff members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff members list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Staff.class)))
            )})
    @GetMapping("/staff")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Staff>> getStaff(){
        return new ResponseEntity<>(staffService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get staff member by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find Staff by Id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Staff.class)))
            )})
    @GetMapping("/staff/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Staff>> getStaffById(@PathVariable Long id){
        return new ResponseEntity<>(staffService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get staff member by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find Staff by Id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Staff.class)))
            )})
    @GetMapping("/staff/role/{role}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Staff>> getStaffByRole(@PathVariable String role){
        return new ResponseEntity<>(staffService.findByRole(role), HttpStatus.OK);
    }

    @Operation(summary = "Add new Staff member")
    @PostMapping("/staff")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Staff> addStaff(@RequestBody Staff staff){
        return new ResponseEntity<>(staffService.addStaff(staff), HttpStatus.OK);
    }

    @Operation(summary = "Modify Staff")
    @PutMapping("/staff/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Staff> modifyStaff(@PathVariable Long id,@RequestBody Staff staff){
        Optional<Staff> s = staffService.findById(id);
        if(s.isPresent()){
            Staff newStaff = staffService.modifyStaff(id, staff);
            return new ResponseEntity<>(newStaff, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete Staff")
    @DeleteMapping("/staff/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id){
        Optional<Staff> s = staffService.findById(id);
        if(s.isPresent()){
            staffService.deleteStaff(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}