package ch.lu.mygym.loginservice;

import ch.lu.mygym.dtos.entities.RoutineEntity;
import ch.lu.mygym.dtos.entities.SetsEntity;
import ch.lu.mygym.dtos.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer>
 {

     List<UserEntity> findAll();

     UserEntity findById(int id);

     UserEntity findByRemoteid(String remoteId);

     UserEntity save(UserEntity entity);


}

