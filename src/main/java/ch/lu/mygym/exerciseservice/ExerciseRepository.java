package ch.lu.mygym.exerciseservice;

import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface ExerciseRepository extends CrudRepository<SetsEntity, Integer> {

    @Query(value = "SELECT x from SetsEntity x where x.exerciseId = ?1 and x.userEntity.userid = ?2 order by x.time")
    List<SetsEntity> getAllSetsOf(int exerciseId, int userId);
}

