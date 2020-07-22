package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.SupersetDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class SetConverter {

    public static List<SetsEntity> convertSetDTOsToSetEntity(SupersetDTO supersetDTO, ExerciseEntity exerciseEntity) {

        LocalDateTime time = LocalDateTime.now();
        return supersetDTO.getSets().stream().map(a -> {
            SetsEntity setEntity = new SetsEntity();
            setEntity.setRepetitions(a.getRepetitions());
            setEntity.setWeight(a.getWeight());
            setEntity.setTime(time);
            return setEntity;
        }).collect(Collectors.toList());
    }

}
