package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.service.ExerciceService;
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

@RestController
public class ExerciceController {
    @Autowired
    private ExerciceService exerciceService;

    @Operation(summary = "Get all Exercices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercices list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Exercice>> getExercices(){
        return new ResponseEntity<>(exerciceService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get exercice by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find exercice by id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Exercice>> getExerciceById(@PathVariable Long id){
        return new ResponseEntity<>(exerciceService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Get exercice by Tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find exercices by tag",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice/tag/{tag}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Exercice>> getExerciceByTag(@PathVariable String tag){
        return new ResponseEntity<>(exerciceService.findByTag(tag), HttpStatus.OK);
    }

}