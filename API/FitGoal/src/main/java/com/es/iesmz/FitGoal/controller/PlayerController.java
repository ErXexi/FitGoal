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
import com.es.iesmz.FitGoal.service.PlayerService;
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
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @Operation(summary = "Get all Players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all players",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayer(){
        return new ResponseEntity<>(playerService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Find player by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by name",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/name/{name}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayerByName(@PathVariable String name){
        return new ResponseEntity<>(playerService.findByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Find player by surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by surname",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/surname/{surname}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayerBySurname(@PathVariable String surname){
        return new ResponseEntity<>(playerService.findBySurname(surname), HttpStatus.OK);
    }

    @Operation(summary = "Find player by team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by team",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/team/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayerByTeam(@PathVariable Long id){
        return new ResponseEntity<>(playerService.findByTeam(id), HttpStatus.OK);
    }

    @Operation(summary = "Find player by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/team/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Player>> getPlayerById(@PathVariable Long id){
        return new ResponseEntity<>(playerService.findById(id), HttpStatus.OK);
    }


}