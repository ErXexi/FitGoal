package com.es.iesmz.FitGoal.controller;



import com.es.iesmz.FitGoal.DTO.Player.DtoPlayer;
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;


//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication
//http://localhost:8080/api/auth/signup?username=Pepe&email=pepe@gmail.com&password=1234&role=admin
@RestController
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;

    @Operation(summary = "Get all Players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all players",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/players")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayers(){
        Set<Player> players = null;
        players = playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @Operation(summary = "Find player by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by name",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/players/name/{name}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayerByName(@PathVariable String name){
        return new ResponseEntity<>(playerService.findByName(name), HttpStatus.OK);
    }

    @Operation(summary = "Find player by surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by surname",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/surname/{surname}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Set<Player>> getPlayerBySurname(@PathVariable String surname){
        return new ResponseEntity<>(playerService.findBySurname(surname), HttpStatus.OK);
    }

    @Operation(summary = "Find player by team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by team",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/team/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF') || hasRole('ROLE_USER')")
    public ResponseEntity<Set<Player>> getPlayerByTeam(@PathVariable Long id){
        Set<Player> players = playerService.findByTeam(id);
        for(Player player : players){
            player.setPhoto(decompressBase64String(player.getPhoto()));
        }
        return new ResponseEntity<>(playerService.findByTeam(id), HttpStatus.OK);
    }

    @Operation(summary = "Find player by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Find player by id",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class)))
            )})
    @GetMapping("/player/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF') || hasRole('ROLE_USER')")
    public ResponseEntity<Optional<Player>> getPlayerById(@PathVariable Long id){
        return new ResponseEntity<>(playerService.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Add new Player")
    @PostMapping("/player")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Player> addPlayer(@RequestBody DtoPlayer player){
        Team team = teamService.findById(player.teamId).orElseThrow();
        Position position = Position.valueOf(player.getPosition());
        player.setPhoto(compressBase64String(player.getPhoto()));
        Player actualPlayer = new Player();
        actualPlayer.setPhoto(player.getPhoto());
        actualPlayer.setName(player.Name);
        actualPlayer.setSurname(player.getSurname());
        actualPlayer.setPositions(position);
        actualPlayer.setTeam(team);
        actualPlayer.setNumber(player.getNumber());
        playerService.addPlayer(actualPlayer);
        return new ResponseEntity<>(actualPlayer, HttpStatus.OK);
    }

    @Operation(summary = "Modify Player")
    @PutMapping("/player/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Player> modifyPlayer(@PathVariable Long id,@RequestBody DtoPlayer player){
        Optional<Player> p = playerService.findById(id);
        if(p.isPresent()){
            Position position = Position.valueOf(player.getPosition());
            Player actualPlayer = p.get();
            actualPlayer.setPhoto(compressBase64String(player.photo));
            actualPlayer.setName(player.Name);
            actualPlayer.setSurname(player.getSurname());
            actualPlayer.setPositions(position);
            Team team = teamService.findById(actualPlayer.getTeam().getId()).orElseThrow();
            actualPlayer.setTeam(team);
            actualPlayer.setNumber(player.getNumber());
            Player newPlayer = playerService.modifyPlayer(id, actualPlayer);
            return new ResponseEntity<>(newPlayer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Delete Player")
    @DeleteMapping("/player/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN') || hasRole('ROLE_STAFF')")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        Optional<Player> p = playerService.findById(id);
        if(p.isPresent()){
            playerService.deletePlayer(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
