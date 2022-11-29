package com.ly.Resolute.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    @Column
    private String fullName;
    @Column
    private long streak = 1;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_routine",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "routine_id"))
    private Set<Routine> routines = new HashSet<>();

    public User(String fullName){
        this.fullName = fullName;
    }

    public long increaseStreak(){
        streak++;
        return streak;
    }

    public void addRoutineToUser(Routine routine){
        routines.add(routine);
    }
}