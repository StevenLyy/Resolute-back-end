package com.ly.Resolute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="musclegroup")
public class Musclegroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "musclegroups", fetch = FetchType.LAZY)
    private Set<Exercise> exercises = new HashSet<>();

    public Musclegroup(String name){
        this.name = name;
    }
}
