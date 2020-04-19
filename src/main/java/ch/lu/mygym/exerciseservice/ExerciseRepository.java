package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<SetsEntity, Integer> {

    List<SetsEntity> findByExerciseId(int exerciseId);

    SetsEntity findById(int id);

    @Query(value = "Select s from SetsEntity s where s.exerciseId = ?1 and s.time = (SELECT max(time) FROM SetsEntity)")
    List<SetsEntity> entitiesWithMaxTimeStamp(int exerciseId);

}

