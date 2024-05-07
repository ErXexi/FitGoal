package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice;
import com.es.iesmz.FitGoal.DTO.Exercice.DtoExerciceOnSessionDelete;
import com.es.iesmz.FitGoal.DTO.Helper.DtoResponse;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.domain.Session;
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

import java.util.List;
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

    @Operation(summary = "Get exercice by Session Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find exercices by session id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice/session/{sessionId}")  // Changed {id} to {sessionId} to match the method parameter
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<List<DtoExercice>> getExerciceBySession(@PathVariable Long sessionId){
        return new ResponseEntity<>(exerciceService.findBySession(sessionId), HttpStatus.OK);
    }


    @Operation(summary = "Add new Exercice")
    @PostMapping("/exercice")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Exercice> addExercice(@RequestBody Exercice exercice){
        Exercice e = exerciceService.addExercice(exercice);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @Operation(summary = "Add Exercice into session")
    @PostMapping("/exercice/session")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> addExerciceIntoSession(@RequestBody DtoSessionAddExercice data){
        exerciceService.addExerciceIntoSession(data);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Modify Exercice")
    @PutMapping("/exercice/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Exercice> modifyExercice(@PathVariable Long id,@RequestBody Exercice exercice){
        Optional<Exercice> ex = exerciceService.findById(id);
        if(ex.isPresent()){
            Exercice newEx = exerciceService.modifyExercice(id, exercice);
            return new ResponseEntity<>(newEx, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete exercice")
    @DeleteMapping("/exercice/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteExercice(@PathVariable Long id){
        Optional<Exercice> ex = exerciceService.findById(id);
        if(ex.isPresent()){
            exerciceService.deleteExerciceFromSessions(id);
            exerciceService.deleteExercice(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete exercice from session")
        @DeleteMapping("/exercice/session")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteExerciceFromSession(@RequestBody DtoExerciceOnSessionDelete data){
        DtoResponse result = exerciceService.deleteExerciceFromSelectedSession(data);
        return result.isSuccess() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}