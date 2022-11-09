package com.ly.Resolute.model;

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

    public Routine(String name, long version) {
        this.name = name;
        this.version = version;
    }

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
    private Set<RoutineExercise> routineExercises = new HashSet<>();

    public void addRoutineExercises(RoutineExercise routineExercise){
        routineExercises.add(routineExercise);
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "routine_exercise",
//            joinColumns = @JoinColumn(name = "routine_id"),
//            inverseJoinColumns = {@JoinColumn(name = "exercise_id"), @Column(name="sets"), @JoinColumn(name = "reps")})
//    private Set<Exercise> exercises = new HashSet<>();
}