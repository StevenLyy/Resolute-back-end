package com.ly.Resolute.resource;

import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.service.MusclegroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/musclegroup")
public class MusclegroupResource {
    private final MusclegroupService mgService;

    @Autowired
    public MusclegroupResource(MusclegroupService mgService){
        this.mgService = mgService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musclegroup> findMusclegroupById(@PathVariable Long id){
        Musclegroup musclegroup = mgService.findMusclegroupById(id);
        return new ResponseEntity<>(musclegroup, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Musclegroup> addMusclegroup(@RequestBody Musclegroup musclegroup){
        Musclegroup newMusclegroup = mgService.addMuscleGroup(musclegroup);
        return new ResponseEntity<>(newMusclegroup, HttpStatus.CREATED);
    }
}
