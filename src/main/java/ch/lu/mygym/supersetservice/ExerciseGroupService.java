package ch.lu.mygym.supersetservice;


import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.entities.WorkoutEntity;
import ch.lu.mygym.dtos.plain.ExerciseGroupDTO;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.workoutservice.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routine-service")
public class ExerciseGroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @PostMapping(value="/get-json-routines", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<ExerciseGroupDTO> getPropertyJSON(@RequestBody WorkoutDTO workout) {

        System.out.println(workout);

        WorkoutEntity workoutEntity = workoutRepository.findById(workout.getId());
        List<RoutineEntity> routines = workoutEntity.getRoutines();

        // List<RoutineEntity> routines = groupRepository.findAll();
        List<ExerciseGroupDTO> exerciseGroups = ExerciseGroupConverter.convertGroupEntitiesToDTO(routines);

        return exerciseGroups;
    }
}
