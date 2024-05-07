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
import com.es.iesmz.FitGoal.service.TagService;
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
public class TagController {

    @Autowired
    TagService tagService;

    @Operation(summary = "Get all tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tag list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Tag.class)))
            )})
    @GetMapping("/tag")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Tag>> getTags(){
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get tag by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find tag by name",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Tag.class)))
            )})
    @GetMapping("/tag/{name}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Optional<Tag>> getTagsByName(@PathVariable String name){
        return new ResponseEntity<>(tagService.findByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Get tags of one exercice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find tags of exercice",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Tag.class))))
    })
    @GetMapping("/tag/exercice/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Tag>> getTagsByExercice(@PathVariable Long id){
        return new ResponseEntity<>(tagService.findByExercice(id), HttpStatus.OK);
    }
    @Operation(summary = "Add new Tag")
    @PostMapping("/tag")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag){
        return new ResponseEntity<>(tagService.addTag(tag), HttpStatus.OK);
    }

    @Operation(summary = "Modify tag")
    @PutMapping("/tag/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Tag> modifyTag(@PathVariable Long id,@RequestBody Tag tag){
        Optional<Tag> t = tagService.findById(id);
        if(t.isPresent()){
            Tag newTag = tagService.modifyTag(id, tag);
            return new ResponseEntity<>(newTag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete tag")
    @DeleteMapping("/tag/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id){
        Optional<Tag> t = tagService.findById(id);
        if(t.isPresent()){
            tagService.deleteTag(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}