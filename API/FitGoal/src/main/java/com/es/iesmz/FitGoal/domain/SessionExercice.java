package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.PERSIST;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "session_Exercice")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SessionExercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Session-Exercise ID", example = "1", required = true)
    private Long id;

    @ManyToOne(cascade = {PERSIST, DETACH})
    @JoinColumn(name = "exercice_id")
    private Exercice exercice;

    @ManyToOne(cascade = {PERSIST, DETACH})
    @JoinColumn(name = "session_id")
    private Session session;

    private Date addedAt;

    public SessionExercice(Exercice exercice, Session session) {
        this.exercice = exercice;
        this.session = session;
        this.addedAt = new Date();
    }
}
