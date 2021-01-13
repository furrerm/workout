package ch.lu.mygym.exerciesService;

import ch.lu.mygym.dtos.entities.ExerciseEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<ExerciseEntity, Integer> {
    List<ExerciseEntity> findAll();
}
