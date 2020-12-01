package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.plain.ExerciseSetContainerDTO;
import ch.lu.mygym.dtos.plain.ExerciseSetDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class ExerciseSetContainerForSetsDTO {
    private int exerciseId;
    private Set<ExerciseSetContainerDTO> exerciseSetContainerDTOs;

    public ExerciseSetContainerForSetsDTO(int exerciseId, Set<ExerciseSetContainerDTO> exerciseSetContainerDTOs) {
        this.exerciseId = exerciseId;
        this.exerciseSetContainerDTOs = exerciseSetContainerDTOs;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public Set<ExerciseSetContainerDTO> getExerciseSetContainerDTOs() {
        return exerciseSetContainerDTOs;
    }
}
