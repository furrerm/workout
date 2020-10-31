package ch.lu.mygym.exerciseservice;


import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.ExerciseSetDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {
/*
    public static List<ExerciseSetDTO> coonvertSetEntitiesToDTOs(List<SetsEntity> setEntities) {
        return setEntities.stream().map(a -> {
            ExerciseSetDTO setDTO = new ExerciseSetDTO();
            setDTO.setId(a.getId());
            setDTO.setRepetitions(a.getRepetitions());
            setDTO.setWeight(a.getWeight());
            return setDTO;
        })
        .collect(Collectors.toList());
    }

    public static ExerciseSetDTO coonvertSetEntityToDTO(SetsEntity setEntity) {
        ExerciseSetDTO setDTO = new ExerciseSetDTO();
        setDTO.setId(setEntity.getId());
        setDTO.setRepetitions(setEntity.getRepetitions());
        setDTO.setWeight(setEntity.getWeight());
        return setDTO;
    }

*/
}
