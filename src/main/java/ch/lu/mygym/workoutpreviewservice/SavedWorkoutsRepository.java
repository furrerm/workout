package ch.lu.mygym.workoutpreviewservice;

import ch.lu.mygym.dtos.entities.SavedWorkoutsEntity;
import ch.lu.mygym.dtos.entities.WorkoutEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavedWorkoutsRepository extends CrudRepository<SavedWorkoutsEntity, Integer> {

    List<SavedWorkoutsEntity> findByUserEntity_UseridOrderById(int userId);
        
}
