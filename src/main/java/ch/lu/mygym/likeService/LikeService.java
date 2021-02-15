package ch.lu.mygym.likeService;

import ch.lu.mygym.dtos.entities.SavedWorkoutsEntity;
import ch.lu.mygym.dtos.entities.WorkoutEntity;
import ch.lu.mygym.dtos.plain.SavedWorkoutDTO;
import ch.lu.mygym.dtos.plain.UserDTO;
import ch.lu.mygym.dtos.plain.WorkoutDTO;
import ch.lu.mygym.dtos.plain.WorkoutSetDTO;
import ch.lu.mygym.exerciseSetsService.ExerciseSetsConverter;
import ch.lu.mygym.loginservice.UserConverter;
import ch.lu.mygym.loginservice.UserRepository;
import ch.lu.mygym.saveworkoutservice.ResponseMessage;
import ch.lu.mygym.saveworkoutservice.WorkoutConverter;
import ch.lu.mygym.workoutpreviewservice.SavedWorkoutsRepository;
import ch.lu.mygym.workoutservice.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@CrossOrigin
@RequestMapping("/like-service")
public class LikeService {

    @Autowired
    private SavedWorkoutsRepository savedWorkoutsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @CrossOrigin
    @PostMapping("/like-workout")
    @ResponseBody
    public String uploadFile(@RequestBody SavedWorkoutDTO savedWorkoutDTO) {
        SavedWorkoutsEntity savedWorkoutsEntity = savedWorkoutsRepository.findByUserEntity_UseridAndWorkoutEntity_Id(savedWorkoutDTO.getUserId(), savedWorkoutDTO.getWorkoutId());
        if(savedWorkoutsEntity != null) {
            savedWorkoutsRepository.delete(savedWorkoutsEntity);
        }
        else {
            SavedWorkoutsEntity workoutEntity = new SavedWorkoutsEntity();
            workoutEntity.setUserEntity(this.userRepository.findById(savedWorkoutDTO.getUserId()));
            workoutEntity.setWorkoutEntity(this.workoutRepository.findById(savedWorkoutDTO.getWorkoutId()).get());
            savedWorkoutsRepository.save(workoutEntity);
        }
        return "{\"var1\": \"ok\"}";
    }
}
