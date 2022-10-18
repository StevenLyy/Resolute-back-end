package com.ly.Resolute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="musclegroup")
public class Musclegroup implements Serializable {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column
    private String musclegroupName;

    @JsonIgnore
    @ManyToMany(mappedBy = "musclegroups", fetch = FetchType.LAZY)
    private Set<Exercise> exercises = new HashSet<>();

    public Musclegroup(){}

    public Musclegroup(String musclegroupName){
        this.musclegroupName = musclegroupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMusclegroupName() {
        return musclegroupName;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }
}
