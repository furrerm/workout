package ch.lu.mygym.workoutservice;

import ch.lu.mygym.dtos.entities.WorkoutEntity;
import ch.lu.mygym.dtos.plain.WorkoutDTO;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutConverter {

    public static List<WorkoutDTO> convertWorkoutEntitiesToDTO(List<WorkoutEntity> workoutEntities) {


        return workoutEntities.stream().map(a -> {
            WorkoutDTO dto =  new WorkoutDTO();
            dto.setId(a.getId());
            dto.setName(a.getName());
            dto.setUserId(a.getUserEntity().getUserid());
            return dto;
        }).collect(Collectors.toList());
    }


}
