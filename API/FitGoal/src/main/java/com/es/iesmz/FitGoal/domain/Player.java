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

    @NotBlank
    @Size(max = 20)
    @Column(name = "surnames")
    @Schema(description = "Player's surnames", example = "Viyuelas Castillo", required = true)
    private String surname;

    @ElementCollection
    @Column(name = "position")
    @Schema(description = "Player's positions", example = "[RB, CB, LB]", required = true)
    private List<Position> positions;

    @NotBlank
    @Size(max = 20)
    @Column(name = "crest")
    @Schema(description = "Player's photo", example = "pathToImage", required = true)
    private String photo;


    @Column(name = "Yellow cards")
    @Schema(description = "Player's yellow cards", example = "1")
    private int yellowCards;

    @Column(name = "Red cards")
    @Schema(description = "Player's red cards", example = "1")
    private int redCards;

    @Column(name = "minutes")
    @Schema(description = "Player's played minutes", example = "200", required = true)
    private int minutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id") // Esto creará una columna de clave foránea en la tabla 'Player'
    private Team team;

    public Player(String name, String surname, List<Position> positions, String photo, int yellowCards, int redCards, int minutes, Team team) {
        this.name = name;
        this.surname = surname;
        this.positions = positions;
        this.photo = photo;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.minutes = minutes;
        this.team = team;
    }
}