package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Session's ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    @Schema(description = "Session's name", example = "CD Iraklis youth sesion", required = true)
    private String name;

    @NotBlank
    @Column(name = "url")
    @Schema(description = "Session's url", example = "pathToSession", required = true)
    private String url;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "session_allowed_users",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> allowedUsers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "Session_exercice",
            joinColumns = @JoinColumn(name = "id_Exercice"),
            inverseJoinColumns = @JoinColumn(name = "id_Session")
    )
    private List<Exercice> exercices = new ArrayList<>();
}
