package com.ly.Resolute.service;

import com.ly.Resolute.exception.RoutineNotFoundException;
import com.ly.Resolute.model.Exercise;
import com.ly.Resolute.model.Routine;
import com.ly.Resolute.model.RoutineExercise;
import com.ly.Resolute.repository.ExerciseRepo;
import com.ly.Resolute.repository.RoutineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutineService {
    private final RoutineRepo routineRepo;
    private final ExerciseRepo exerciseRepo;

    @Autowired
    public RoutineService(RoutineRepo routineRepo, ExerciseRepo exerciseRepo) {
        this.routineRepo = routineRepo;
        this.exerciseRepo = exerciseRepo;
    }

    public Routine findRoutineById(long id){
        return routineRepo.findById(id).orElseThrow(() -> new RoutineNotFoundException("Routine by id" + id + " not found"));
    }

    public List<Routine> findAllRoutines(){
        return routineRepo.findAll();
    }

    public Routine addRoutine(Routine routine){
        return routineRepo.save(routine);
    }

    public Routine addExerciseToRoutine(long routineId, long exerciseId, int sets, int reps){
        Routine routine = findRoutineById(routineId);
        Exercise exercise = exerciseRepo.findById(exerciseId).orElseThrow();
        RoutineExercise routineExercise = new RoutineExercise(routine, exercise, sets, reps);
        routine.addRoutineExercises(routineExercise);
        return routineRepo.save(routine);
    }

    public void deleteRoutine(long id){
        routineRepo.deleteById(id);
    }
}
