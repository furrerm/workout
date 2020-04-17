package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<SetsEntity, Integer>
 {

    List<SetsEntity> findByExerciseId(int exerciseId);

    SetsEntity findById(int id);


}

