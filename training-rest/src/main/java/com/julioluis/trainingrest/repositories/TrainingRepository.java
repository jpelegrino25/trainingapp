package com.julioluis.trainingrest.repositories;

import com.julioluis.trainingrest.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Integer> {

    @Query(value = "SELECT * FROM training WHERE status=1",nativeQuery = true)
    List<Training> findAll();
    Optional<Training> findById(Integer id);
}
