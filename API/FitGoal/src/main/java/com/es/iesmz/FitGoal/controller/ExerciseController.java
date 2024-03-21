package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.domain.ERole;
import com.es.iesmz.FitGoal.domain.Exercise;
import com.es.iesmz.FitGoal.domain.Role;
import com.es.iesmz.FitGoal.domain.User;
import com.es.iesmz.FitGoal.payload.request.UserLoginRequest;
import com.es.iesmz.FitGoal.payload.request.UserSignupRequest;
import com.es.iesmz.FitGoal.payload.response.JwtResponse;
import com.es.iesmz.FitGoal.payload.response.MessageResponse;
import com.es.iesmz.FitGoal.repository.RoleRepository;
import com.es.iesmz.FitGoal.repository.UserRepository;
import com.es.iesmz.FitGoal.security.jwt.JwtUtils;
import com.es.iesmz.FitGoal.security.services.UserDetailsImpl;
import com.es.iesmz.FitGoal.service.ExerciseService;
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

@RestController
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @Operation(summary = "Get all Exercises")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercise.class)))
            )})
    @GetMapping("/exercise")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Exercise>> getExercises(){
        return new ResponseEntity<>(exerciseService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get exercise by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercise.class)))
            )})
    @GetMapping("/exercise/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Exercise>> getExerciseById(@PathVariable Long id){
        return new ResponseEntity<>(exerciseService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get exercise by Tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercise.class)))
            )})
    @GetMapping("/exercise/tag/{tag}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Exercise>> getExerciseByTag(@PathVariable String tag){
        return new ResponseEntity<>(exerciseService.findByTag(tag), HttpStatus.OK);
    }




}