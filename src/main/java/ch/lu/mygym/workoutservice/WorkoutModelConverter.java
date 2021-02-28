package ch.lu.mygym.workoutservice;

import ch.lu.mygym.dtos.entities.*;
import ch.lu.mygym.dtos.plain.*;
import ch.lu.mygym.loginservice.UserConverter;

import java.util.*;
import java.util.stream.Collectors;

public class WorkoutModelConverter {

    public Set<WorkoutDTO> convertWorkoutEntitiesToDTO(List<WorkoutEntity> workoutEntities, UserDTO currentUser) {
        Set<WorkoutDTO> workouts = workoutEntities.
                stream().
                map(a -> convertWorkoutEntityToDTO(a, currentUser)).
                collect(Collectors.toSet());
        return workouts;
    }

    private WorkoutDTO convertWorkoutEntityToDTO(WorkoutEntity workoutEntity, UserDTO currentUser) {

        List<PhaseDayExerciseRelationEntity> workout_Relations = workoutEntity.getPhaseDayExerciseRelationEntities();
        Set<DayEntity> days = workout_Relations.stream().map(b -> b.getDayEntity()).collect(Collectors.toSet());
        WorkoutDTO workoutDTO = new WorkoutDTO.WorkoutBuilder().
                withId(workoutEntity.getId()).
                withName(workoutEntity.getName()).
                withImageUrl(workoutEntity.getImageUrl()).
                withCreator(new UserConverter().convertUserEntityToDTO(workoutEntity.getUserEntity())).
                withDays(days.
                        stream().
                        map(b -> convertDayEntityToDTO(b, workout_Relations)).
                        sorted().
                        collect(Collectors.toList())).
                withIsSavedFromCurrentUser(isWorkoutSavedByUser(workoutEntity, currentUser)).
                build();
        return workoutDTO;
    }

    private DayDTO convertDayEntityToDTO(DayEntity dayEntity, List<PhaseDayExerciseRelationEntity> workout_Relations) {

        List<PhaseDayExerciseRelationEntity> workout_day_Relations = workout_Relations.
                stream().
                filter(c -> c.getDayEntity().equals(dayEntity)).
                // auf tag ist gefiltert somit alle mit gleicher phase entfernen und ersten nehmen
                        collect(Collectors.toList());

        Map<Integer, PhaseEntity> phases = workout_day_Relations.stream()
                .collect(Collectors.toMap(z -> z.getPhaseOrder(), z -> z.getPhaseEntity(), (entity1, entity2) -> {
                            return entity1;
                        }
                ));

        DayDTO dayDTO = new DayDTO.Builder().
                withId(dayEntity.getId()).
                withName(dayEntity.getName()).
                withPhases(phases.
                        keySet().
                        stream().
                        map(d -> convertPhaseEntityToDTO(phases.get(d), d, workout_day_Relations)).
                        sorted().
                        collect(Collectors.toList())).
                build();

        return dayDTO;
    }

    private PhaseDTO convertPhaseEntityToDTO(PhaseEntity phaseEntity, int order, List<PhaseDayExerciseRelationEntity> workout_day_Relations) {

        Set<PhaseDayExerciseRelationEntity> workout_day_phase_Relations = workout_day_Relations.
                stream().
                filter(z -> z.getPhaseEntity().equals(phaseEntity)).
                collect(Collectors.toSet());

        PhaseDTO phaseDTO = new PhaseDTO.
                Builder().
                withId(phaseEntity.getId()).
                withName(phaseEntity.getName()).
                withOrder(order).
                withExercises(workout_day_phase_Relations.
                        stream().
                        map(re -> this.convertExerciseEntityToDTO(re)).
                        collect(Collectors.toList())).
                build();
        return phaseDTO;
    }

    private ExerciseDTO convertExerciseEntityToDTO(PhaseDayExerciseRelationEntity phaseDayExerciseRelationEntity) {
        ExerciseEntity exerciseEntity = phaseDayExerciseRelationEntity.getExerciseEntity();
        return new ExerciseDTO.
                Builder().
                withId(exerciseEntity.getId()).
                withName(exerciseEntity.getName()).
                withOrder(phaseDayExerciseRelationEntity.getExerciseOrder()).
                withVideoUrl(exerciseEntity.getVideoUrl()).
                withImage(exerciseEntity.getImage()).
                withTimeBased(phaseDayExerciseRelationEntity.isTimeBased()).
                withTimeLength(phaseDayExerciseRelationEntity.getTimeLength()).
                withWeight(phaseDayExerciseRelationEntity.isWeight()).
                build();
    }

    private boolean isWorkoutSavedByUser(WorkoutEntity workoutEntity, UserDTO currentUser) {
        return workoutEntity.
                getSavedWorkoutsEntity().
                stream().
                anyMatch(savedWorkoutsEntity -> savedWorkoutsEntity.getUserEntity().getUserid() == currentUser.getId());
    }
}
