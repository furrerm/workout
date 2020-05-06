package ch.lu.mygym.userservice;

import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<UserEntity, Integer>
 {

     List<UserEntity> findAll();

     RoutineEntity findById(int id);

     UserEntity save(UserEntity entity);


}

