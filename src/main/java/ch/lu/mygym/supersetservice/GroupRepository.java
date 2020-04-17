package ch.lu.mygym.supersetservice;

import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<RoutineEntity, Integer>
 {

     List<RoutineEntity> findAll();

     RoutineEntity findById(int id);


}

