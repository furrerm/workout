package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;

import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface SetRepository extends CrudRepository<SetsEntity, Integer>
 {

     List<SetsEntity> findAll();


     RoutineEntity findById(int id);

/*
     @Query(value = "SELECT max(time) FROM SetsEntity")
     public LocalDateTime max();
*/
}

