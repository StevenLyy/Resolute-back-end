package com.ly.Resolute.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
@DynamicUpdate
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;
    @Column
    private String fullName;
    @Column
    private long streak = 1;

    public User(String fullName){
        this.fullName = fullName;
    }

    public long increaseStreak(){
        streak++;
        return streak;
    }
}