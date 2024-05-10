package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.domain.*;
import com.es.iesmz.FitGoal.payload.request.UserLoginRequest;
import com.es.iesmz.FitGoal.payload.request.UserSignupRequest;
import com.es.iesmz.FitGoal.payload.response.JwtResponse;
import com.es.iesmz.FitGoal.payload.response.MessageResponse;
import com.es.iesmz.FitGoal.repository.RoleRepository;
import com.es.iesmz.FitGoal.repository.UserRepository;
import com.es.iesmz.FitGoal.security.jwt.JwtUtils;
import com.es.iesmz.FitGoal.security.services.UserDetailsImpl;
import com.es.iesmz.FitGoal.service.TeamService;
import com.es.iesmz.FitGoal.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//http://localhost:8080/api/auth/signup?username=Pepe&email=pepe@gmail.com&password=1234&role=admin
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TeamController {
    @Autowired
    TeamService teamService;

    @Operation(summary = "Get all teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))
            )})
    @GetMapping("/team")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Team>> getTeam(){
        return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))
            )})
    @GetMapping("/team/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Team>> getTeamById(@PathVariable Long id){
        return new ResponseEntity<>(teamService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get team by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find team by name",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))
            )})
    @GetMapping("/team/name/{name}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Team>> getTeamByName(@PathVariable String name){
        return new ResponseEntity<>(teamService.findByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Get team by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find team by user id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class)))
            )})
    @GetMapping("/team/user/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Team> getTeamByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(teamService.findByUser(userId), HttpStatus.OK);
    }
    @Operation(summary = "Add new Team")
    @PostMapping("/team")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        return new ResponseEntity<>(teamService.addTeam(team), HttpStatus.OK);
    }

    @Operation(summary = "Modify Team")
    @PutMapping("/team/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Team> modifyTeam(@PathVariable Long id,@RequestBody Team team){
        Optional<Team> t = teamService.findById(id);
        if(t.isPresent()){
            Team newTeam = teamService.modifyTeam(id, team);
            return new ResponseEntity<>(newTeam, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete Team")
    @DeleteMapping("/team/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id){
        Optional<Team> t = teamService.findById(id);
        if(t.isPresent()){
            teamService.deleteTeam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}