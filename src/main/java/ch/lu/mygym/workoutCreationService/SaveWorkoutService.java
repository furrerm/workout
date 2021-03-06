package ch.lu.mygym.workoutCreationService;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.lu.mygym.dtos.entities.PhaseEntity;
import ch.lu.mygym.dtos.plain.DayDTO;
import ch.lu.mygym.dtos.plain.ExerciseDTO;
import ch.lu.mygym.dtos.plain.PhaseDTO;
import ch.lu.mygym.exerciesService.ExerciseRepository;
import ch.lu.mygym.exerciseSetsService.ExerciseSetsConverter;
import ch.lu.mygym.loginservice.UserRepository;
import ch.lu.mygym.dtos.entities.WorkoutEntity;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.workoutservice.WorkoutRepository;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@CrossOrigin
@RequestMapping("/save-workout-service")
public class SaveWorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository1;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private PhasesRepository phasesRepository;

    @Autowired
    private DayRepository dayRepository;

    @CrossOrigin
    @PostMapping(value = "/upload-workout",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public String uploadWorkout(@RequestBody WorkoutDTO workout) {
        this.saveWorkout(workout);
        return "{\"var1\": \"ok\"}";
    }

    private void saveWorkout(WorkoutDTO workoutDTO) {
        WorkoutConverter converter = new WorkoutConverter(userRepository1, exerciseRepository, phasesRepository, dayRepository);
        WorkoutEntity workoutEntity = converter.convertDTOToEntity(workoutDTO);
        this.workoutRepository.save(workoutEntity);
    }

    @CrossOrigin
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam MultipartFile file, String path) {

        InputStream inputStream = null;
        System.out.println(path);
        try {
            inputStream = new BufferedInputStream(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // todo: change the filname to searchable like /1/workoutname/frontpicture.<png.jpg...>
        String filename = path;
        this.saveImage(inputStream, filename);
        ResponseEntity<ResponseMessage> re = ResponseEntity.ok().body(new ResponseMessage("message from backend saveSetServeice"));
        return "{\"var1\": \"ok\"}";
    }

    private void saveImage(InputStream file, String fileName) {
        String bucketName = "elasticbeanstalk-eu-west-2-345269114307";
        String filePath = System.getProperty("user.dir") + "/webapps/ROOT/.aws/credentials";
        AWSCredentialsProvider awsCredentialsProvider = new ProfileCredentialsProvider(filePath, "app-1-development");

        try {

            ClientConfiguration clientConfiguration = new ClientConfiguration();
            clientConfiguration.setConnectionTimeout(50000);
            clientConfiguration.setMaxConnections(500);
            clientConfiguration.setSocketTimeout(50000);
            clientConfiguration.setMaxErrorRetry(10);

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().
                    withCredentials(awsCredentialsProvider).
                    withClientConfiguration(clientConfiguration).
                    build();

            s3Client.putObject(bucketName, fileName, file, new ObjectMetadata());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-phases", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PhaseDTO> getPhases() {
        return IterableUtils.toList(phasesRepository.findAll())
                .stream()
                .map(phaseEntity -> new PhaseDTO.Builder()
                        .withId(phaseEntity.getId())
                        .withName(phaseEntity.getName())
                        .withExercises(Collections.EMPTY_LIST)
                        .build())
                .collect(Collectors.toList());
    }

    @CrossOrigin // (origins = "http://localhost:4200")
    @GetMapping(value = "/get-days", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<DayDTO> getDays() {
        return IterableUtils.toList(dayRepository.findAll())
                .stream()
                .map(dayEntity -> new DayDTO.Builder()
                        .withId(dayEntity.getId())
                        .withName(dayEntity.getName())
                        .withPhases(Collections.EMPTY_LIST)
                        .build())
                .collect(Collectors.toList());
    }
}
