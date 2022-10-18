package com.ly.Resolute.service;

import com.ly.Resolute.exception.MusclegroupNotFoundException;
import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.MusclegroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MusclegroupService {
    private final MusclegroupRepo mgR;

    @Autowired
    public MusclegroupService(MusclegroupRepo mgR){
        this.mgR = mgR;
    }

    public Musclegroup addMuscleGroup(Musclegroup musclegroup){
        return mgR.save(musclegroup);
    }

    public Musclegroup findMusclegroupById(Long id) {
        return mgR.findById(id)
                .orElseThrow(() -> new MusclegroupNotFoundException("Musclegroup by id" + id + " not found"));
    }
}