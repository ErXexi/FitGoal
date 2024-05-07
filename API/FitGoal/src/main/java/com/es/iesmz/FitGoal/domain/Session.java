package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "session")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonIgnore
    private User creator;

    public Session(Long id, String name, Set<User> allowedUsers, User creator) {
        this.id = id;
        this.name = name;
        this.allowedUsers = allowedUsers;
        this.creator = creator;
    }
}
