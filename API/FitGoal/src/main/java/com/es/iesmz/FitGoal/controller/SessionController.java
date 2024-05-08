package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.DTO.Helper.DtoResponse;
import com.es.iesmz.FitGoal.DTO.Session.DtoSession;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionRemove;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionResponse;
import com.es.iesmz.FitGoal.domain.*;
import com.es.iesmz.FitGoal.service.ExerciceService;
import com.es.iesmz.FitGoal.service.SessionService;
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
public class SessionController {
    @Autowired
    SessionService sessionService;
    @Autowired
    ExerciceService exerciceService;

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

    @GetMapping("/session/image/{sessionId}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Exercice> getImageOfFirstExerciceFromSession(@PathVariable Long sessionId){
        return new ResponseEntity<>(exerciceService.getImageFirstExerciceFromSession(sessionId), HttpStatus.OK);
    }



    @Operation(summary = "Add new Session")
    @PostMapping("/session/{userId}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<DtoResponse> addSession(@RequestBody DtoSession data, @PathVariable Long userId){
        DtoResponse response = sessionService.addSession(data, userId);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
    @Operation(summary = "Add exercice to session")
    @PostMapping("/session/exercice/add")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<DtoResponse> addExerciceToSession(@RequestBody DtoSessionAddExercice data) {
        DtoResponse response = sessionService.addExerciceToSession(data);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "Remove exercice to session")
    @PostMapping("/session/exercice/remove/{exerciceIndex}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<DtoResponse> RemoveExerciceToSession(
            @RequestBody DtoSessionRemove data,
            @PathVariable int exerciceIndex
    ) {
        DtoResponse response = sessionService.removeExerciceToSession(data.SessionId, exerciceIndex);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "Modify Session")
    @PutMapping("/session/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<DtoResponse> modifySession(@PathVariable Long id,@RequestBody DtoSession data){
        DtoResponse response = sessionService.modifySession(id, data);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "Delete Session")
    @DeleteMapping("/session/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<DtoResponse> deleteSession(@PathVariable Long id){
        DtoResponse response = sessionService.deleteSession(id);
        HttpStatus status = response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }
}