package ch.lu.mygym.saveworkoutservice;

import ch.lu.mygym.dtos.entities.*;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.loginservice.UserRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkoutConverter {
    private final UserRepository userRepository1;

    public WorkoutConverter(UserRepository userRepository1) {
        this.userRepository1 = userRepository1;
    }

    public WorkoutEntity convertDTOToEntity(WorkoutDTO workoutDTO) {
        WorkoutEntity workoutEntity = new WorkoutEntity();
        UserEntity creator = this.userRepository1.findById(workoutDTO.getCreator().getId());
        workoutEntity.setUserEntity(creator);
        workoutEntity.setName(workoutDTO.getName());
        workoutEntity.setImageUrl(workoutDTO.getPreviewImageUrl());

        List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelations = new ArrayList<>();
        workoutDTO.getDays().forEach(dayDTO -> {
            DayEntity dayEntity = new DayEntity();
            dayEntity.setName(dayDTO.getName());
            dayDTO.getPhases().forEach(phaseDTO -> {
                PhaseEntity phaseEntity = new PhaseEntity();
                phaseEntity.setName(phaseDTO.getName());
                phaseDTO.getExercises().forEach(exerciseDTO -> {
                    ExerciseEntity exerciseEntity = new ExerciseEntity();
                    exerciseEntity.setName(exerciseDTO.getName());
                    PhaseDayExerciseRelationEntity phaseDayExerciseRelationEntity = new PhaseDayExerciseRelationEntity();
                    phaseDayExerciseRelationEntity.setExerciseEntity(exerciseEntity);
                    phaseDayExerciseRelationEntity.setPhaseEntity(phaseEntity);
                    phaseDayExerciseRelationEntity.setDayEntity(dayEntity);
                    phaseDayExerciseRelationEntity.setPhaseOrder(phaseDTO.getOrder());
                    phaseDayExerciseRelationEntity.setExerciseOrder(exerciseDTO.getOrder());
                    phaseDayExerciseRelationEntity.setWorkoutEntity(workoutEntity);
                    phaseDayExerciseRelations.add(phaseDayExerciseRelationEntity);
                });
            });
        });
        workoutEntity.setPhaseDayExerciseRelationEntities(phaseDayExerciseRelations);
        return workoutEntity;
    }
}
