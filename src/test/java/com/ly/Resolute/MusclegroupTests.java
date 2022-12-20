package com.ly.Resolute;

import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.MusclegroupRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNull;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class MusclegroupTests {
    @Autowired
    private MusclegroupRepo musclegroupRepo;

    @Test
    public void createAndRetrieveMusclegroup() {
        Musclegroup genericMusclegroup = musclegroupRepo
                .save(new Musclegroup("Chest"));
        Optional<Musclegroup> foundMusclegroup = musclegroupRepo
                .findById(genericMusclegroup.getId());

        assertNotNull(foundMusclegroup);
        assertEquals("Test", "Chest", foundMusclegroup.get().getName());
    }

    @Test
    public void createAndUpdateMusclegroup(){
        Musclegroup genericMusclegroup = musclegroupRepo
                .save(new Musclegroup("Chest"));
        genericMusclegroup.setName("Back");
        musclegroupRepo.save(genericMusclegroup);
        Optional<Musclegroup> foundMusclegroup = musclegroupRepo
                .findById(genericMusclegroup.getId());
        assertNotNull(foundMusclegroup);
        assertEquals("Test","Back", foundMusclegroup.get().getName());
    }
}