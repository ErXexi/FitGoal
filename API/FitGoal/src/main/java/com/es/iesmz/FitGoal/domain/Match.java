package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="Match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "Match's ID", example = "1", required = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_team_id")
    private Team localTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visiting_team_id")
    private Team visitingTeam;

    // Otros atributos y métodos...

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate date;

    @NotBlank
    @Size(max = 5)
    @Column(name = "time")
    @Schema(description = "Match's time", example = "12:30", required = true)
    private String time;

    @Size(max = 5)
    @Column(name = "result")
    @Schema(description = "Match's result", example = "3-3")
    private String result;

    @Column(name = "day")
    @Schema(description = "Match's day", example = "1", required = true)
    private int day;

    @Column(name = "isEnded")
    @Schema(description = "Status of the match", example = "0", required = true)
    private boolean isEnded;

    public void setResult(int LocalGoals, int VisitingGoals){
        result = LocalGoals + "-" + VisitingGoals;
    }
}
