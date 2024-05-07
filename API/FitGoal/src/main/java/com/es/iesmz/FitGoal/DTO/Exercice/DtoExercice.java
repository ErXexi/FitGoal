package com.es.iesmz.FitGoal.DTO.Exercice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

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
    private LocalDateTime addedAtSession; // Make sure this matches what you need

    public DtoExercice(Long id, String name, String description, String image, String video, LocalDateTime addedAtSession) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.video = video;
        this.addedAtSession = addedAtSession;
    }
}
