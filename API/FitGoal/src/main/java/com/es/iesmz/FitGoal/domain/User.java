package com.es.iesmz.FitGoal.domain;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "password")
        })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Schema(description = "User's ID", example = "1", required = true)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "name")
    @Schema(description = "User's name", example = "Pepe", required = true)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(name = "surnames")
    @Schema(description = "User's surnames", example = "Viyuelas Castillo", required = true)
    private String surname;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    @Schema(description = "User's e-mail", example = "Isaac@example.com", required = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    @Schema(description = "User's password", example = "12345678", required = true)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "User_Rol",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team")
    private Team team;

    @ManyToMany(mappedBy = "allowedUsers", fetch = FetchType.LAZY)
    private Set<Session> allowedSessions = new HashSet<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Session> createdSessions = new ArrayList<>();

    public User(String name, String surname, String email, String password, Team team) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.team = team;
    }

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
