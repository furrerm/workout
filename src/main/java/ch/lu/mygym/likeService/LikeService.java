package ch.lu.mygym.likeService;

import ch.lu.mygym.dtos.entities.SavedWorkoutsEntity;
import ch.lu.mygym.dtos.plain.SavedWorkoutDTO;
import ch.lu.mygym.loginservice.UserRepository;
import ch.lu.mygym.workoutpreviewservice.SavedWorkoutsRepository;
import ch.lu.mygym.workoutservice.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
