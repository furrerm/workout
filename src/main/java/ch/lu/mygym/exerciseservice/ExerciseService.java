package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.CustomerRepository;
import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.SetDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/exercise")
public class ExerciseService {
    @Autowired  //This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ExerciseRepository exerciseRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value="/get-sets", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<SetDTO> getPropertyJSON(@RequestParam(defaultValue="") String exercise) {

        System.out.println(exercise);
        Gson jsonHandler = new Gson();
        ExerciseDTO exerciseDTO = jsonHandler.fromJson(exercise, ExerciseDTO.class);

        int id = exerciseDTO.getId();

        List<SetsEntity> setEntities = exerciseRepository.findByExerciseId(id);
        List<SetDTO> setDTOs = DTOConverter.coonvertSetEntitiesToDTOs(setEntities);

        return setDTOs;
    }
}
