package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface ExerciseRepository extends CrudRepository<SetsEntity, Integer> {

    List<SetsEntity> findById(int exerciseId);

    // SetsEntity findById(int id);

    // s.id is bullshit and must be changed
    // @Query(value = "Select s from SetsEntity s where s.id = ?1 and s.time = (SELECT max(time) FROM SetsEntity)")
    // @Query(value = "Select s from SetsEntity s where s.time = (SELECT max(time) FROM SetsEntity)")
    // @Query(value = "Select s from SetsEntity s where s.exerciseEntity.id = ?1")
    // @Query(value = "select s from SetsEntity s where s.exerciseEntity.id = ?1 and s.time = (SELECT max(s2.time) FROM SetsEntity s2 where s2.exerciseEntity.id = ?1)")
    // List<SetsEntity> entitiesWithMaxTimeStamp(int exerciseId);

    @Query(value = "SELECT max(s.time) FROM SetsEntity s where s.exerciseId = ?1 and s.userEntity.userid = ?2")
    Timestamp getMaxTime(int exerciseId, int userId);

    @Query(value = "SELECT x from SetsEntity x where x.time = (SELECT max(s.time) FROM SetsEntity s where s.exerciseId = ?1 and s.userEntity.userid = ?2) and x.exerciseId = ?1 and x.userEntity.userid = ?2")
    List<SetsEntity> getLastSetOf(int exerciseId, int userId);

    @Query(value = "SELECT x from SetsEntity x where x.exerciseId = ?1 and x.userEntity.userid = ?2 order by x.time")
    List<SetsEntity> getAllSetsOf(int exerciseId, int userId);
}

