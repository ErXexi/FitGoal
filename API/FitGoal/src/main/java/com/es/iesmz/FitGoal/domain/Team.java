package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity (name="Team")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Team {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonProperty("id")
        @Schema(description = "Team's id", example = "1", required = true)
        private Long id;

        @NotBlank
        @Size(max = 50)
        @Column(name = "name")
        @Schema(description = "Team's name", example = "CD Iraklis", required = true)
        private String name;

        @NotBlank
        @Size(max = 20)
        @Column(name = "province")
        @Schema(description = "Team's province", example = "Alicante", required = true)
        private String province;

        @NotBlank
        @Size(max = 20)
        @Column(name = "city")
        @Schema(description = "Team's city", example = "San Vicente del Raspeig", required = true)
        private String city;

        @NotBlank
        @Size(max = 20)
        @Column(name = "crest")
        @Schema(description = "Team's crest", example = "pathToImage", required = true)
        private String crest;


        @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        @JsonIgnore
        private List<Player> players;


        @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
        private List<Staff> staff = new ArrayList<>();

        @OneToMany(mappedBy = "localTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Match> localMatches = new ArrayList<>();

        @OneToMany(mappedBy = "visitingTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Match> visitingMatches = new ArrayList<>();
        public Team(String name, String province, String city, String crest) {
                this.name = name;
                this.province = province;
                this.city = city;
                this.crest = crest;
        }


}
