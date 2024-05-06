package com.es.iesmz.FitGoal.DTO.Exercice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoExercice {
    public Long id;
    public String name;
    public String description;
    public String image;
    public String video;
    public Date addedAtSession;

    public DtoExercice(String name, String description, String image, String video, Date addedAtSession) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.video = video;
        this.addedAtSession = addedAtSession;
    }
}
