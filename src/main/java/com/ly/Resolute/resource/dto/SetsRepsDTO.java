package com.ly.Resolute.resource.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetsRepsDTO {
    int sets;
    int reps;

    public SetsRepsDTO() {

    }

    public SetsRepsDTO(int sets, int reps) {
        this.sets = sets;
        this.reps = reps;
    }
}
