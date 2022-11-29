package com.ly.Resolute.resource;

import com.ly.Resolute.model.Routine;
import com.ly.Resolute.service.RoutineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@RequestMapping("api/v1/routines")
public class RoutineResource {
    private final RoutineService routineService;

    public RoutineResource(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable("id") Long id){
        Routine routine = routineService.findRoutineById(id);
        return new ResponseEntity<>(routine, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines(){
        List<Routine> routines = routineService.findAllRoutines();
        return new ResponseEntity<>(routines, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Routine> addRoutine(@RequestBody Routine routine){
        Routine newRoutine = routineService.addRoutine(routine);
        return new ResponseEntity<>(newRoutine, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoutineById (@PathVariable("id") Long id) {
        routineService.deleteRoutine(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/{routineId}/exercises/{exerciseId}")
    public ResponseEntity<Routine> addExerciseToRoutine(@PathVariable long routineId, @PathVariable long exerciseId, @RequestBody SetsRepsDTO setsReps){
        Routine routine = routineService.addExerciseToRoutine(routineId, exerciseId, setsReps.getSets(), setsReps.getReps());
        return new ResponseEntity<>(routine, HttpStatus.ACCEPTED);
    }
}
