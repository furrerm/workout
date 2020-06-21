package ch.lu.mygym.workoutservice;

import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.entities.UserEntity;
import ch.lu.mygym.dtos.entities.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<WorkoutEntity, Integer>
 {

     List<WorkoutEntity> findAll();

     WorkoutEntity findById(int id);

     WorkoutEntity save(WorkoutEntity entity);


}

