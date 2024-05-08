package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.DTO.Exercice.DtoExercice;
import com.es.iesmz.FitGoal.DTO.Exercice.DtoExerciceOnSessionDelete;
import com.es.iesmz.FitGoal.DTO.Helper.DtoResponse;
import com.es.iesmz.FitGoal.DTO.Session.DtoSessionAddExercice;
import com.es.iesmz.FitGoal.domain.Exercice;
import com.es.iesmz.FitGoal.service.ExerciceService;
import com.es.iesmz.FitGoal.service.TagService;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;


//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//http://localhost:8080/api/auth/signup?username=Pepe&email=pepe@gmail.com&password=1234&role=admin

@RestController
public class ExerciceController {
    @Autowired
    private ExerciceService exerciceService;
    @Autowired
    private TagService tagService;

    @Operation(summary = "Get all Exercices")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exercices list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Exercice>> getExercices(){
        Set<Exercice> exercices = exerciceService.findAll();
        for (Exercice e: exercices){
            e.setImage(decompressBase64String(e.getImage()));
        }
        return new ResponseEntity<>(exercices, HttpStatus.OK);
    }

    @Operation(summary = "Get exercice by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find exercice by id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Exercice> getExerciceById(@PathVariable Long id){
        Exercice exercice = exerciceService.findById(id).orElseThrow();
        exercice.setImage(decompressBase64String(exercice.getImage()));

        return new ResponseEntity<>(exercice, HttpStatus.OK);
    }

    @Operation(summary = "Get exercice by Tag")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find exercices by tag",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice/tag/{tag}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Exercice>> getExerciceByTag(@PathVariable String tag){
        Set<Exercice> exercices = exerciceService.findByTag(tag);
        for (Exercice e: exercices){
            e.setImage(decompressBase64String(e.getImage()));
        }
        return new ResponseEntity<>(exercices, HttpStatus.OK);
    }

    @Operation(summary = "Get exercice by Session Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find exercices by session id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Exercice.class)))
            )})
    @GetMapping("/exercice/session/{sessionId}")  // Changed {id} to {sessionId} to match the method parameter
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<List<DtoExercice>> getExerciceBySession(@PathVariable Long sessionId){
        List<DtoExercice> exerciceList = exerciceService.findBySession(sessionId);
        for (DtoExercice exercice: exerciceList){
            exercice.setImage(decompressBase64String(exercice.getImage()));
            exercice.setTags(tagService.findByExercice(exercice.getId()));
        }
        return new ResponseEntity<>(exerciceList, HttpStatus.OK);
    }


    @PostMapping("/exercice")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Exercice> addExercice(@RequestBody Exercice exercice) {
        exercice.setImage(compressBase64String(exercice.getImage()));
        System.out.println(exercice.getImage());
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
    public static String compressBase64String(String base64String) {
        try {//  w    w  w.    de  m   o  2   s.  c   o   m
            // Step 1: Decode the Base64 string to get the original binary data.
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);

            // Step 2: Compress the binary data using gzip compression.
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, new Deflater(Deflater.BEST_COMPRESSION));
            deflaterOutputStream.write(decodedBytes);
            deflaterOutputStream.finish();

            byte[] compressedBytes = outputStream.toByteArray();

            // Step 3: Encode the compressed binary data back to Base64 format.
            String compressedBase64String = Base64.getEncoder().encodeToString(compressedBytes);

            return compressedBase64String;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decompressBase64String(String compressedBase64String) {
        try {
            byte[] compressedBytes = Base64.getDecoder().decode(compressedBase64String);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedBytes);
            InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream, new Inflater());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inflaterInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inflaterInputStream.close();
            byte[] decompressedBytes = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(decompressedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}