package ch.lu.mygym.exerciseservice;


import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.SetDTO;

import java.util.List;
import java.util.stream.Collectors;

public class DTOConverter {

    public static List<SetDTO> coonvertSetEntitiesToDTOs(List<SetsEntity> setEntities) {
        return setEntities.stream().map(a -> {
            SetDTO setDTO = new SetDTO();
            setDTO.setId(a.getId());
            setDTO.setRepetitions(a.getRepetitions());
            setDTO.setWeight(a.getWeight());
            return setDTO;
        })
        .collect(Collectors.toList());
    }

    public static SetDTO coonvertSetEntityToDTO(SetsEntity setEntity) {
        SetDTO setDTO = new SetDTO();
        setDTO.setId(setEntity.getId());
        setDTO.setRepetitions(setEntity.getRepetitions());
        setDTO.setWeight(setEntity.getWeight());
        return setDTO;
    }


}
