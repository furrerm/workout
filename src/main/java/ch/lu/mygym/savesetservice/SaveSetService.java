package ch.lu.mygym.savesetservice;


import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.plain.*;


import ch.lu.mygym.loginservice.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/save-sets-service")
public class SaveSetService {

    @Autowired
    private SetRepository setRepository;
    @Autowired
    private ExerciseRepositoryForSaveSetService exerciseRepository;
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String saveExerciseSets(@RequestBody WorkoutSetDTO workoutSetDTO) {
        System.out.println(workoutSetDTO);

        DayDTO dayDTO = workoutSetDTO.getDayDTO();

        ExerciseEntity exerciseEntity = exerciseRepository.findById(dayDTO.getId());
        // extract the flattening part in separeted generic class for reuse

        List<ExerciseDTO> exercisesDTOs = dayDTO.getPhases().
                stream().
                map(PhaseDTO::getExercises).
                collect(Collectors.toList()).
                stream().
                flatMap(SortedSet::stream).
                collect(Collectors.toList());

        List<ExerciseSetContainerForSetsDTO> exerciseSetContainerForSetsDTO = exercisesDTOs.
                stream().
                map(a -> new ExerciseSetContainerForSetsDTO(a.getId(), a.getSetsContainer())).
                collect(Collectors.toList());

        UserEntity userEntity = userRepository.findById(workoutSetDTO.getUserId());
        List<SetsEntity> setsEntities = this.convertExerciseSetContainerToSetsEntity(exerciseSetContainerForSetsDTO, userEntity);
        /*
        List<ExerciseSetContainerDTO> sortedSetContainers = exerciseSetContainerForSetsDTO.stream().sorted(Comparator.comparing(a -> a.getTimeOfExercise())).collect(Collectors.toList());
        ExerciseSetContainerDTO latestSortedSetContainer = sortedSetContainers.get(sortedSetContainers.size()-1);

        List<SetsEntity> setsEntities = this.convertExerciseSetContainerToSetsEntity(latestSortedSetContainer);

*/
        this.setRepository.saveAll(setsEntities);


        System.out.println(dayDTO);
        // setRepository.saveAll(dayDTO);

        return null;
    }

    // could one make a converter on the corresponding dto ???
    private List<SetsEntity> convertExerciseSetContainerToSetsEntity(List<ExerciseSetContainerForSetsDTO> exerciseSetContainerForSetsDTO, UserEntity userEntity) {

        List<SetsEntity> sortedSetContainers = exerciseSetContainerForSetsDTO.stream().map(a -> {
                    List<ExerciseSetContainerDTO> sortedExerciseSetContainerDTOS = a.getExerciseSetContainerDTOs().stream().sorted(Comparator.comparing(b -> b.getTimeOfExercise())).collect(Collectors.toList());
                    ExerciseSetContainerDTO latestSortedSetContainer = sortedExerciseSetContainerDTOS.get(sortedExerciseSetContainerDTOS.size() - 1);
                    List<SetsEntity> setsEntities = this.convertExerciseSetContainerToSetsEntity(latestSortedSetContainer, userEntity, a.getExerciseId());
                    return setsEntities;
                }
        ).collect(Collectors.toList()).
                stream().
                flatMap(List::stream).
                collect(Collectors.toList());

return sortedSetContainers;
/*
        return exerciseSetContainerForSetsDTO.
                stream().
                map(a -> convertExerciseSetContainerToSetsEntity(a.getExerciseSetContainerDTOs())).
                collect(Collectors.toList()).
                stream().
                flatMap(List::stream).
                collect(Collectors.toList());
 */
    }

    private List<SetsEntity> convertExerciseSetContainerToSetsEntity(ExerciseSetContainerDTO exerciseSetContainerDTO, UserEntity userEntity, int exerciseId) {
        LocalDateTime timestamp = exerciseSetContainerDTO.getTimeOfExercise();
        List<SetsEntity> setsEntities = exerciseSetContainerDTO.getExerciseSets().stream().map(a -> {
            SetsEntity setsEntity = new SetsEntity();
            setsEntity.setTime(timestamp);
            setsEntity.setRepetitions(a.getRepetitions());
            setsEntity.setWeight(a.getWeight());
            setsEntity.setUserEntity(userEntity);
            setsEntity.setExerciseId(exerciseId);
            return setsEntity;
        }).collect(Collectors.toList());
        return setsEntities;
    }
}
