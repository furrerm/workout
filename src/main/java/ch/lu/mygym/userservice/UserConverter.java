package ch.lu.mygym.userservice;

import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.ExerciseGroupDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static List<ExerciseGroupDTO> convertGroupEntitiesToDTO(List<RoutineEntity> routineEntities) {


        return routineEntities.stream().map(a -> {
            ExerciseGroupDTO dto =  new ExerciseGroupDTO(a.getName(), a.getId());
            List<ExerciseDTO> exercises = a.getExercises().stream().map(b -> new ExerciseDTO(b.getId(), b.getName())).collect(Collectors.toList());
            dto.setExercises(exercises);
            return dto;
        }).collect(Collectors.toList());
    }


}
