package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<SetsEntity, Integer> {

    List<SetsEntity> findById(int exerciseId);

    // SetsEntity findById(int id);

    // s.id is bullshit and must be changed
    // @Query(value = "Select s from SetsEntity s where s.id = ?1 and s.time = (SELECT max(time) FROM SetsEntity)")
    // @Query(value = "Select s from SetsEntity s where s.time = (SELECT max(time) FROM SetsEntity)")
    // @Query(value = "Select s from SetsEntity s where s.exerciseEntity.id = ?1")
    @Query(value = "select s from SetsEntity s where s.exerciseEntity.id = ?1 and s.time = (SELECT max(s2.time) FROM SetsEntity s2 where s2.exerciseEntity.id = ?1)")
    List<SetsEntity> entitiesWithMaxTimeStamp(int exerciseId);

    @Query(value = "SELECT max(time) FROM SetsEntity")
    List<SetsEntity> maxTime();

}

