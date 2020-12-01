package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.dtos.plain.ExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/exercise")
public class ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-sets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ExerciseDTO> getPropertyJSON(@RequestParam(defaultValue = "") List<Integer> exerciseIds) {
        // Add the user to parameter list and consider by reading from db
        ExerciseSetConverter exerciseSetConverter = new ExerciseSetConverter();
        return exerciseIds.stream()
                .map(a -> exerciseSetConverter.createPseudoExerciseDTO(a, exerciseRepository.getAllSetsOf(a, 1)))
                .collect(Collectors.toList());
    }
}
