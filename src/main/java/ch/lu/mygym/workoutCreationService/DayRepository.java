package ch.lu.mygym.workoutCreationService;

import ch.lu.mygym.dtos.entities.DayEntity;
import org.springframework.data.repository.CrudRepository;

public interface DayRepository extends CrudRepository<DayEntity, Integer> {

}
