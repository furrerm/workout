package ch.lu.mygym.exerciesService;

import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.UserDTO;
import ch.lu.mygym.exerciseSetsService.ExerciseSetsConverter;
import ch.lu.mygym.exerciseSetsService.ExerciseSetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/exercise")
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @PostMapping(
            value = "/get-all-exercises",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ExerciseDTO> getAllExercises() {
        ExerciseConverter exerciseConverter = new ExerciseConverter();
        List<ExerciseEntity> exerciseEntities = exerciseRepository.findAll();
        List<ExerciseDTO> exerciseDTOS = exerciseConverter.convertToDTO(exerciseEntities);
        return exerciseDTOS;
    }
}
