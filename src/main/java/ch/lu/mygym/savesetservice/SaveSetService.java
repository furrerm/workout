package ch.lu.mygym.savesetservice;


import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.SupersetDTO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RestController
@RequestMapping("/save-sets-service")
public class SaveSetService {

    @Autowired
    private SetRepository setRepository;
    @Autowired
    private ExerciseRepositoryForSaveSetService exerciseRepository;

    @CrossOrigin
    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String getPropertyJSON(@RequestBody SupersetDTO exercise) {
        System.out.println(exercise);
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exercise.getId());
        List<SetsEntity> sets = SetConverter.convertSetDTOsToSetEntity(exercise, exerciseEntity);

        System.out.println(sets);
        setRepository.saveAll(sets);

        return null;
    }

}
