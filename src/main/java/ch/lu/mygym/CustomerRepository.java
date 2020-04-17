package ch.lu.mygym;

import ch.lu.mygym.dtos.entities.ExerciseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<ExerciseEntity, Long>
 {

    // List<ExerciseEntity> findByLastName(String lastName);

    ExerciseEntity findById(long id);


}

