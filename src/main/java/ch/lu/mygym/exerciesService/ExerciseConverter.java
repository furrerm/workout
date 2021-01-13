package ch.lu.mygym.exerciesService;

import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseConverter {
    List<ExerciseDTO> convertToDTO(List<ExerciseEntity> exerciseEntities) {
        return exerciseEntities.stream().
                map(exerciseEntity -> new ExerciseDTO.Builder().
                        withId(exerciseEntity.getId()).
                        withName(exerciseEntity.getName()).
                        withOrder(0).
                        withSets(null).
                        build()).collect(Collectors.toList());
    }
}
