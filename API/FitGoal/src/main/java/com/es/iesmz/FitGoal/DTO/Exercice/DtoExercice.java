package com.es.iesmz.FitGoal.DTO.Exercice;

import com.es.iesmz.FitGoal.domain.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DtoExercice {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String video;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addedAtSession;
    private Set<Tag> tags;

    public DtoExercice(Long id, String name, String description, String video, String image, LocalDateTime addedAtSession, Set<Tag> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.video = video;
        this.image = image;
        this.addedAtSession = addedAtSession;
        this.tags = tags;
    }

    public DtoExercice(Long id, String name, String description, String image, String video, LocalDateTime addedAtSession) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.video = video;
        this.addedAtSession = addedAtSession;
    }
}
