package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepositoryForSaveSetService extends CrudRepository<ExerciseEntity, Integer>
 {

     List<ExerciseEntity> findAll();


     ExerciseEntity findById(int id);

/*
     @Query(value = "SELECT max(time) FROM SetsEntity")
     public LocalDateTime max();
*/
}

