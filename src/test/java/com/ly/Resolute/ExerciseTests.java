package com.ly.Resolute;

import com.ly.Resolute.model.Exercise;
import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.ExerciseRepo;
import com.ly.Resolute.repository.MusclegroupRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class ExerciseTests {
    @Autowired
    private MusclegroupRepo musclegroupRepo;

    @Autowired
    private ExerciseRepo exerciseRepo;

    @Test
    public void createAndRetrieveExercise() {
        Exercise genericExercise = exerciseRepo
                .save(new Exercise("Bench Press", "BP details"));
        Optional<Exercise> foundExercise = exerciseRepo
                .findById(genericExercise.getId());

        assertNotNull(foundExercise);
        assertEquals("Bench Press", foundExercise.get().getName());
    }

    @Test
    public void createAndRetrieveExerciseWithMusclegroups(){
        Exercise genericExercise = exerciseRepo
                .save(new Exercise("Bench Press", "BP details"));
        Musclegroup musclegroup1 = musclegroupRepo
                .save(new Musclegroup("Biceps"));
        Musclegroup musclegroup2 = musclegroupRepo
                .save(new Musclegroup("Triceps"));
    }
}