package ch.lu.mygym.workoutCreationService;

import ch.lu.mygym.dtos.entities.PhaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhasesRepository extends CrudRepository<PhaseEntity, Integer> {

}
