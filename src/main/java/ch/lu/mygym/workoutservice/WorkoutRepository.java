package ch.lu.mygym.workoutservice;

import ch.lu.mygym.dtos.entities.WorkoutEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkoutRepository extends CrudRepository<WorkoutEntity, Integer> {

     List<WorkoutEntity> findAll();
}

