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
import com.es.iesmz.FitGoal.service.SessionService;
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
public class SessionController {
    @Autowired
    SessionService sessionService;
    @Operation(summary = "Get all Sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Session list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Session.class)))
            )})
    @GetMapping("/session")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Session>> getSession(){
        return new ResponseEntity<>(sessionService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get session by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find session by id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Session.class)))
            )})
    @GetMapping("/session/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Session>> getSessionById(@PathVariable Long id){
        return new ResponseEntity<>(sessionService.findById(id), HttpStatus.OK);
    }
    @Operation(summary = "Get session by creator id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find season by creator id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Session.class)))
            )})
    @GetMapping("/session/creator-id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Session>> getSessionByCreatorId(@PathVariable Long id){
        return new ResponseEntity<>(sessionService.findByCreatorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Add new Session")
    @PostMapping("/session/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Session> addSession(@RequestBody Session session, @PathVariable Long userId){
        try{
            Session newSession = sessionService.addSession(session, userId);
            return new ResponseEntity<>(newSession, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Add exercice to session")
    @PostMapping("/session")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Session> addSession(
            @RequestParam(name = "sessionId")Long sessionId,
            @RequestParam(name = "exerciceId")Long exerciceId
            ){
        try{
            Session newSession = sessionService.addExerciceToSession(exerciceId, sessionId);
            return new ResponseEntity<>(newSession, HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modify Session")
    @PutMapping("/session/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Session> modifySession(@PathVariable Long id,@RequestBody Session session){
        Optional<Session> s = sessionService.findById(id);
        if(s.isPresent()){
            Session newSession = sessionService.modifySession(id, session);
            return new ResponseEntity<>(newSession, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete Session")
    @DeleteMapping("/session/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id){
        Optional<Session> s = sessionService.findById(id);
        if(s.isPresent()){
            sessionService.deleteSession(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}