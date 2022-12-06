package com.ly.Resolute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="routine")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column
    private String name;

    @Column
    private long version;

    @JsonIgnore
    @ManyToMany(mappedBy = "routines", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    public Routine(String name, long version) {
        this.name = name;
        this.version = version;
    }

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    @OrderBy("id ASC")
    private Set<RoutineExercise> routineExercises = new HashSet<>();

    public void addRoutineExercises(RoutineExercise routineExercise){
        routineExercises.add(routineExercise);
    }
}