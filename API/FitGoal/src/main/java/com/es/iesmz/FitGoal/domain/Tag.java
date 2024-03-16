package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Tag's ID", example = "1", required = true)
    private Long id;
    @NotBlank
    @Size(max = 5)
    @Column(name = "name")
    @Schema(description = "Tag's name", example = "12:30", required = true)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Exercise> exercises = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }
}
