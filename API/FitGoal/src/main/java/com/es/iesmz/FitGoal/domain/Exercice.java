package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Exercice")
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Exercice's ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "name")
    @Schema(description = "Exercice's name", example = "Running", required = true)
    private String name;

    @NotBlank
    @Size(max = 250)
    @Column(name = "description")
    @Schema(description = "Exercice's description", example = """
            This pose activates your glutes to lift your hips, which helps train your core while toning your butt and thighs.
                        
            Start on your back. Bend your knees and plant your feet on the floor at hip width. Place your hands at your sides, palms down.
            Tighten your core and glutes.
            Raise your hips until your knees are in line with your shoulders.
            Hold for 10–30 seconds.
            Repeat 3–5 times.
            """, required = true)
    private String description;

    @NotBlank
    @Column(name = "image")
    @Lob
    @Schema(description = "Exercice's image", example = "pathToImage", required = true)
    private String image;

    @Column(name = "video")
    @Lob
    @Schema(description = "Exercice's video", example = "pathToImage", required = true)
    private String video;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(
            name = "Exercice_Tag",
            joinColumns = @JoinColumn(name = "id_Exercice"),
            inverseJoinColumns = @JoinColumn(name = "id_Tag")
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "exercice")
    @JsonIgnore
    private Set<SessionExercice> sessionExercices = new HashSet<>();
}
