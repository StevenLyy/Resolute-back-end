package com.ly.Resolute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="routine_exercise")
public class RoutineExercise {
    @JsonIgnore
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;
    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    private int sets;
    private int reps;

    public RoutineExercise(Routine routine, Exercise exercise, int sets, int reps) {
        this.routine = routine;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }
}
