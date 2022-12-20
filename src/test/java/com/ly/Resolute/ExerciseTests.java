package com.ly.Resolute;

import com.ly.Resolute.model.Exercise;
import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.ExerciseRepo;
import com.ly.Resolute.repository.MusclegroupRepo;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.Iterator;
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

        Optional<Exercise> foundExercise = exerciseRepo.findById(genericExercise.getId());

        assertNotNull(foundExercise);
        assertEquals("Bench Press", foundExercise.get().getName());
    }

    @Transactional
    @Test
    public void createAndRetrieveExerciseWithMusclegroups(){
        Exercise genericExercise = exerciseRepo
                .save(new Exercise("Dumbbell curl", "Dumbbell details"));
        Musclegroup musclegroup1 = new Musclegroup("Biceps");
        Musclegroup musclegroup2 = new Musclegroup("Triceps");

        musclegroupRepo.save(musclegroup1);
        musclegroupRepo.save(musclegroup2);

        genericExercise.addMusclegroupToExercise(musclegroup1);
        genericExercise.addMusclegroupToExercise(musclegroup2);
        exerciseRepo.save(genericExercise);

        Optional<Exercise> foundExercise = exerciseRepo.findById(genericExercise.getId());
        assertNotNull(foundExercise);
        assertEquals("Dumbbell curl", foundExercise.get().getName());
        assertEquals("Dumbbell details", foundExercise.get().getDetails());

        Iterator<Musclegroup> iterator = foundExercise.get().getMusclegroups().iterator();
        assertEquals(musclegroup2, iterator.next());
        assertEquals(musclegroup1, iterator.next());
    }
}