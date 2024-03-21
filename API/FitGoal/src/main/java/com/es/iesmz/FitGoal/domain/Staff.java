package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="Staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Staff's ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    @Schema(description = "Staff's name", example = "Pepe", required = true)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "surnames")
    @Schema(description = "Staff's surnames", example = "Viyuelas Castillo", required = true)
    private String surname;

    @Column(name = "role")
    @Schema(description = "Staff's role", example = "Coach", required = true)
    private String role;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public Staff(String name, String surname, String role, Team team) {
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.team = team;
    }
}
