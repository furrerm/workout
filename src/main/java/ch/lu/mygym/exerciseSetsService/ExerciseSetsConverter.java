package ch.lu.mygym.exerciseSetsService;

import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.ExerciseSetContainerDTO;
import ch.lu.mygym.dtos.plain.ExerciseSetDTO;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ExerciseSetsConverter {

    public ExerciseDTO createPseudoExerciseDTO(int exerciseID, List<SetsEntity> setsEntities){
        return new ExerciseDTO.Builder().withId(exerciseID).withSets(this.converSetsEntitiesToDTOs(setsEntities)).build();
    }

    private Set<ExerciseSetContainerDTO> converSetsEntitiesToDTOs(List<SetsEntity> setsEntities) {
        Map<LocalDateTime, List<SetsEntity>> setsEntitiesGroupedByDate = this.groupByTimeStamp(setsEntities);
        return setsEntitiesGroupedByDate.values().stream().map(a -> this.convertToDTO(a)).collect(Collectors.toSet());
    }

    private ExerciseSetContainerDTO convertToDTO(List<SetsEntity> setsEntities) {
        return new ExerciseSetContainerDTO.Builder().
                withTimeOfExercise(setsEntities.get(0).getTime()).
                withExerciseSets(
                        setsEntities.stream().map(a -> convertSetsEntityToDTO(a)).collect(Collectors.toList())
                ).build();
    }

    private ExerciseSetDTO convertSetsEntityToDTO(SetsEntity setsEntity) {
        return new ExerciseSetDTO.Builder().
                withId(setsEntity.getId()).
                withRepetitions(setsEntity.getRepetitions()).
                withWeight(setsEntity.getWeight()).
                build();
    }

    private Map<LocalDateTime, List<SetsEntity>> groupByTimeStamp(List<SetsEntity> setsEntity) {
        Map<LocalDateTime, List<SetsEntity>> setsEntitiesGroupedByDate = new HashMap<>();
        setsEntity.forEach(a -> {
            if (setsEntitiesGroupedByDate.containsKey(a.getTime())) {
                setsEntitiesGroupedByDate.get(a.getTime()).add(a);
            } else {
                List<SetsEntity> setsEntitiesWithSameTimeStamp = new ArrayList<>();
                setsEntitiesWithSameTimeStamp.add(a);
                setsEntitiesGroupedByDate.put(a.getTime(), setsEntitiesWithSameTimeStamp);
            }
        });
        return setsEntitiesGroupedByDate;
    }
}
