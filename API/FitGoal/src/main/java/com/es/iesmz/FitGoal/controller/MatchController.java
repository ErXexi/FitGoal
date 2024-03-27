package com.es.iesmz.FitGoal.controller;

import com.es.iesmz.FitGoal.domain.Match;
import com.es.iesmz.FitGoal.service.MatchService;
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
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//http://localhost:8080/api/auth/signup?username=Pepe&email=pepe@gmail.com&password=1234&role=admin

@RestController
public class MatchController {
    @Autowired
    private MatchService matchService;

    @Operation(summary = "Get all Matches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))
            )})
    @GetMapping("/matches")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Match>> getMatches(){
        return new ResponseEntity<>(matchService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get match by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))
            )})
    @GetMapping("/match/id/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Match>> getMatchById(@PathVariable Long id){
        return new ResponseEntity<>(matchService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get Matches by day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))
            )})
    @GetMapping("/matches/day/{day}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Match>> getMatchByDay(@PathVariable int day){
        return new ResponseEntity<>(matchService.findByDay(day), HttpStatus.OK);
    }

    @Operation(summary = "Get match by local team id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))
            )})
    @GetMapping("/matches/local/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Match>> getMatchByLocalId(@PathVariable int id){
        return new ResponseEntity<>(matchService.findByLocalTeamId(id), HttpStatus.OK);
    }

    @Operation(summary = "Get match by visiting team id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de puntos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class)))
            )})
    @GetMapping("/matches/visiting/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Match>> getMatchByVisitingId(@PathVariable int id){
        return new ResponseEntity<>(matchService.findByVisitingTeamId(id), HttpStatus.OK);
    }
}
