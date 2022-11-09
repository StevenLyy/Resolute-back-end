package com.ly.Resolute.service;

import com.ly.Resolute.exception.ExerciseNotFoundException;
import com.ly.Resolute.exception.MusclegroupNotFoundException;
import com.ly.Resolute.model.Exercise;
import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.ExerciseRepo;
import com.ly.Resolute.repository.MusclegroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepo exerciseRepo;
    private final MusclegroupRepo musclegroupRepo;

    @Autowired
    public ExerciseService(ExerciseRepo exerciseRepo, MusclegroupRepo musclegroupRepo) {
        this.exerciseRepo = exerciseRepo;
        this.musclegroupRepo = musclegroupRepo;
    }

    public Exercise findById(Long id){
        return exerciseRepo.findById(id)
                .orElseThrow( () -> new ExerciseNotFoundException("Exercise by id" + id + "not found"));
    }

    public List<Exercise> findAllExercises(){
        return exerciseRepo.findAll();
    }

    public Exercise addExercise(Exercise exercise){
        return exerciseRepo.save(exercise);
    }

    public Exercise updateExercise(Exercise exercise, Long id){
        Exercise updatedExercise = exerciseRepo.findById(id)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise by id" + id + " not found"));
        updatedExercise.setName(exercise.getName());
        updatedExercise.setDetails(exercise.getDetails());
        return exerciseRepo.save(updatedExercise);
    }

    public void deleteExercise(Long id){
        exerciseRepo.deleteById(id);
    }

    public Exercise addMusclegroupToExercise(Long exerciseId, Long musclegroupId){
        Exercise exercise = exerciseRepo.findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise by id" + exerciseId + " not found"));
        Musclegroup musclegroup = musclegroupRepo.findById(musclegroupId)
                .orElseThrow(() -> new MusclegroupNotFoundException("Musclegroup by id" + musclegroupId + " not found"));
        exercise.addMusclegroupToExercise(musclegroup);
        return exerciseRepo.save(exercise);
    }
}
