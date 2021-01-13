package ch.lu.mygym.exerciseSetsService;

import ch.lu.mygym.dtos.entities.SetsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseSetsRepository extends CrudRepository<SetsEntity, Integer> {

    @Query(value = "SELECT x from SetsEntity x where x.exerciseId = ?1 and x.userEntity.userid = ?2 order by x.time")
    List<SetsEntity> getAllSetsOf(int exerciseId, int userId);
}

