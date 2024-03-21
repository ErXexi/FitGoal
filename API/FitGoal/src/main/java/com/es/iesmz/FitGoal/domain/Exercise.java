package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="Exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Exercise's ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    @Schema(description = "Exercise's name", example = "Running", required = true)
    private String name;

    @NotBlank
    @Size(max = 250)
    @Column(name = "description")
    @Schema(description = "Exercise's description", example = """
            This pose activates your glutes to lift your hips, which helps train your core while toning your butt and thighs.
                        
            Start on your back. Bend your knees and plant your feet on the floor at hip width. Place your hands at your sides, palms down.
            Tighten your core and glutes.
            Raise your hips until your knees are in line with your shoulders.
            Hold for 10–30 seconds.
            Repeat 3–5 times.
            """, required = true)
    private String description;

    @NotBlank
    @Column(name = "video")
    @Schema(description = "Exercise's video", example = "pathToVideo", required = true)
    private String video;

    @NotBlank
    @Column(name = "image")
    @Schema(description = "Exercise's image", example = "pathToImage", required = true)
    private String image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "Exercise_Tag",
            joinColumns = @JoinColumn(name = "id_Exercise"),
            inverseJoinColumns = @JoinColumn(name = "id_Tag")
    )
    private Set<Tag> tags = new HashSet<>();

}
