package ch.lu.mygym.exerciseSetsService;

import ch.lu.mygym.dtos.plain.ExerciseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RestController
@RequestMapping("/exercise-set")
public class ExerciseSetsService {

    @Autowired
    private ExerciseSetsRepository exerciseRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-sets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ExerciseDTO> getPropertyJSON(@RequestParam Map<String, String> requestParams) {
        // todo: handle exception if not integer
        List<Integer> exerciseIds = Stream.of(requestParams.get("exerciseIds")
                .split(",", -1))
                .map(a -> Integer.parseInt(a))
                .collect(Collectors.toList());

        int userIdAsInteger = Integer.parseInt(requestParams.get("userId"));
        ExerciseSetsConverter exerciseSetConverter = new ExerciseSetsConverter();
        return exerciseIds.stream()
                .map(a -> exerciseSetConverter.createPseudoExerciseDTO(a, exerciseRepository.getAllSetsOf(a, userIdAsInteger)))
                .collect(Collectors.toList());
    }
}
