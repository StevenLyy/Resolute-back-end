package com.ly.Resolute.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="exercise")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column
    private String name;

    @Column
    private String details;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "exercise_musclegroup",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "musclegroup_id"))
    private Set<Musclegroup> musclegroups = new HashSet<>();

    public Exercise() {}

    public Exercise(String name, @Nullable String details){
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public long getId() {
        return id;
    }

    public Set<Musclegroup> getMusclegroups() {
        return musclegroups;
    }

    public void addMusclegroupToExercise(Musclegroup musclegroup){
        musclegroups.add(musclegroup);
    }
}
