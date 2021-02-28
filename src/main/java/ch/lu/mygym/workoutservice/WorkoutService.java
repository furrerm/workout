package ch.lu.mygym.workoutservice;


import ch.lu.mygym.dtos.entities.SavedWorkoutsEntity;
import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.entities.WorkoutEntity;

import ch.lu.mygym.dtos.plain.UserDTO;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.loginservice.UserRepository;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RestController
@RequestMapping("/workout-service")
public class WorkoutService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @CrossOrigin // (origins = "http://localhost:4200")
    @PostMapping(value = "/get-workouts",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Set<WorkoutDTO> getSavedWorkoutsFromUser(@RequestBody UserDTO user) {

        UserEntity userEntity = userRepository.findById(user.getId());

        List<SavedWorkoutsEntity> savedWorkouts = userEntity.getSavedWorkoutEntities();
        List<WorkoutEntity> workouts = savedWorkouts.stream().
                map(a -> a.getWorkoutEntity()).
                collect(Collectors.toList());
        WorkoutModelConverter workoutConverter = new WorkoutModelConverter();
        Set<WorkoutDTO> workoutDTOs = workoutConverter.convertWorkoutEntitiesToDTO(workouts, user);

        return workoutDTOs;
    }

    @CrossOrigin // (origins = "http://localhost:4200")
    @PostMapping(value = "/get-workouts-with-search-criteria",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public Set<WorkoutDTO> getWorkoutsWithCriteria(@RequestBody UserDTO user) {

        List<WorkoutEntity> workouts = this.workoutRepository.findAll();
        WorkoutModelConverter workoutConverter = new WorkoutModelConverter();
        Set<WorkoutDTO> workoutDTOs = workoutConverter.convertWorkoutEntitiesToDTO(workouts, user);

        // add image
        return workoutDTOs;
    }
}
