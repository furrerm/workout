package ch.lu.mygym.savesetservice;

import ch.lu.mygym.dtos.entities.SetsEntity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SetRepository extends CrudRepository<SetsEntity, Integer> {
}

