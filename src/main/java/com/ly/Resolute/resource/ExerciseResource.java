package com.ly.Resolute.resource;

import com.ly.Resolute.model.Exercise;
import com.ly.Resolute.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/exercise")
public class ExerciseResource {
    private final ExerciseService exerciseService;

    public ExerciseResource(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") Long id){
        Exercise exercise = exerciseService.findExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Exercise>> getAllExercises(){
        List<Exercise> exercises = exerciseService.findAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.ACCEPTED);
    }

    @PostMapping("/add")
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise){
        Exercise newExercise = exerciseService.addExercise(exercise);
        return new ResponseEntity<>(newExercise, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Exercise> updateExercise(@RequestBody Exercise exercise, @PathVariable Long id){
        Exercise updatedExercise = exerciseService.updateExercise(exercise, id);
        return new ResponseEntity<>(updatedExercise, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExerciseById (@PathVariable("id") Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{exerciseId}/musclegroups/{musclegroupId}")
    public ResponseEntity<Exercise> addMusclegroupToExercise(@PathVariable long exerciseId, @PathVariable long musclegroupId){
        Exercise exercise = exerciseService.addMusclegroupToExercise(exerciseId, musclegroupId);
        return new ResponseEntity<>(exercise, HttpStatus.ACCEPTED);
    }
}

