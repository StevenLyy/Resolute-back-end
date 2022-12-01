package com.ly.Resolute.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @ManyToMany(fetch = FetchType.LAZY)
    @OrderBy("name ASC")
    @JoinTable(name = "exercise_musclegroup",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "musclegroup_id"))
    private Set<Musclegroup> musclegroups = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private Set<RoutineExercise> routineExercises = new HashSet<>();

    public Exercise(String name, @Nullable String details){
        this.name = name;
        this.details = details;
    }

    public void addMusclegroupToExercise(Musclegroup musclegroup){
        musclegroups.add(musclegroup);
    }
}
