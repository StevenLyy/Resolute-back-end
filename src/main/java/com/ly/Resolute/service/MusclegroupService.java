package com.ly.Resolute.service;

import com.ly.Resolute.exception.MusclegroupNotFoundException;
import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.MusclegroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MusclegroupService {
    private final MusclegroupRepo mgRepo;

    @Autowired
    public MusclegroupService(MusclegroupRepo mgR){
        this.mgRepo = mgR;
    }

    public Musclegroup addMuscleGroup(Musclegroup musclegroup){
        return mgRepo.save(musclegroup);
    }

    public List<Musclegroup> getAllMusclegroups(){
        return mgRepo.findAll();
    }
    public Musclegroup findMusclegroupById(Long id) {
        return mgRepo.findById(id)
                .orElseThrow(() -> new MusclegroupNotFoundException("Musclegroup by id" + id + " not found"));
    }
}