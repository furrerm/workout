package ch.lu.mygym.workoutCreationService;

import ch.lu.mygym.dtos.entities.*;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.exerciesService.ExerciseRepository;
import ch.lu.mygym.loginservice.UserRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkoutConverter {
    private final UserRepository userRepository1;
    private final ExerciseRepository exerciseRepository;
    private final PhasesRepository phasesRepository;
    private final DayRepository dayRepository;
    public WorkoutConverter(UserRepository userRepository1, ExerciseRepository exerciseRepository, PhasesRepository phasesRepository, DayRepository dayRepository) {
        this.userRepository1 = userRepository1;
        this.exerciseRepository = exerciseRepository;
        this.phasesRepository = phasesRepository;
        this.dayRepository = dayRepository;
    }

    public WorkoutEntity convertDTOToEntity(WorkoutDTO workoutDTO) {
        WorkoutEntity workoutEntity = new WorkoutEntity();
        UserEntity creator = this.userRepository1.findById(workoutDTO.getCreator().getId());
        workoutEntity.setUserEntity(creator);
        workoutEntity.setName(workoutDTO.getName());
        workoutEntity.setImageUrl(workoutDTO.getPreviewImageUrl());

        List<PhaseDayExerciseRelationEntity> phaseDayExerciseRelations = new ArrayList<>();
        workoutDTO.getDays().forEach(dayDTO -> {
            DayEntity dayEntity = this.dayRepository.findById(dayDTO.getId()).orElseThrow(() -> new RuntimeException("DayDTO not found"));
            dayDTO.getPhases().forEach(phaseDTO -> {
                PhaseEntity phaseEntity = phasesRepository.findById(phaseDTO.getId()).orElseThrow(() -> new RuntimeException("phase was not found"));
                phaseDTO.getExercises().forEach(exerciseDTO -> {
                    ExerciseEntity exerciseEntity = this.exerciseRepository.findById(exerciseDTO.getId()).orElseThrow(() -> new RuntimeException("exercise does not exist"));
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
