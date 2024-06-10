package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Player's ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    @Schema(description = "Player's name", example = "Pepe", required = true)
    private String name;

    @Column(name = "number")
    @Schema(description = "Player's number", example = "1", required = true)
    private int number;



    @NotBlank
    @Size(max = 20)
    @Column(name = "surnames")
    @Schema(description = "Player's surnames", example = "Viyuelas Castillo", required = true)
    private String surname;

    @Column(name = "position")
    @Schema(description = "Player's positions", example = "[RB, CB, LB]", required = true)
    private Position positions;

    @NotBlank
    @Lob
    @Column(name = "photo")
    @Schema(description = "Player's photo", example = "pathToImage", required = true)
    private String photo;


    @Column(name = "Yellow_cards")
    @Schema(description = "Player's yellow cards", example = "1")
    private int yellowCards;

    @Column(name = "Red_cards")
    @Schema(description = "Player's red cards", example = "1")
    private int redCards;

    @Column(name = "minutes")
    @Schema(description = "Player's played minutes", example = "200", required = true)
    private int minutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player(String name, String surname, Position positions, String photo, Team team) {
        this.name = name;
        this.surname = surname;
        this.positions = positions;
        this.photo = photo;
        this.team = team;
    }


}