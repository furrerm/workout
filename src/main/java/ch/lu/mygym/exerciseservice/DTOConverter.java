package ch.lu.mygym.exerciseservice;


import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.SetDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {

    public static List<SetDTO> coonvertSetEntitiesToDTOs(List<SetsEntity> setEntities) {
        return setEntities.stream().map(a -> new SetDTO(a.getId(), a.getWeight(), a.getRepetitions())).collect(Collectors.toList());
    }

    public static SetDTO coonvertSetEntityToDTO(SetsEntity setEntity) {
        return new SetDTO(setEntity.getId(), setEntity.getWeight(), setEntity.getRepetitions());
    }
}
