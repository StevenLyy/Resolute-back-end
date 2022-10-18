package com.ly.Resolute.service;

import com.ly.Resolute.exception.ExerciseNotFoundException;
import com.ly.Resolute.exception.MusclegroupNotFoundException;
import com.ly.Resolute.model.Exercise;
import com.ly.Resolute.model.Musclegroup;
import com.ly.Resolute.repository.ExerciseRepo;
import com.ly.Resolute.repository.MusclegroupRepo;

public class ExerciseMusclegroupService {
    private final ExerciseRepo exerciseRepo;
    private final MusclegroupRepo musclegroupRepo;

    public ExerciseMusclegroupService(ExerciseRepo exerciseRepo, MusclegroupRepo musclegroupRepo) {
        this.exerciseRepo = exerciseRepo;
        this.musclegroupRepo = musclegroupRepo;
    }


}
