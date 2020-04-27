package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.plain.SupersetDTO;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RestController
@RequestMapping("/save-sets-service")
public class SaveSetService {

    @Autowired  //This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private SetRepository setRepository;

    @CrossOrigin
    @PostMapping(value = "/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String getPropertyJSON(@RequestBody String exercise) {
        String exerciseDecoded;
        try {
            exerciseDecoded = URLDecoder.decode(exercise, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
        String exerciseDecodedAsJsonString = exerciseDecoded.substring(exerciseDecoded.indexOf("{"), exerciseDecoded.lastIndexOf("}") + 1);
        Gson jsonHandler = new Gson();

        SupersetDTO supersetDTO = jsonHandler.fromJson(exerciseDecodedAsJsonString, SupersetDTO.class);

        List<SetsEntity> sets = SetConverter.convertSetDTOsToSetEntity(supersetDTO);

        setRepository.saveAll(sets);

        return null;
    }

}
