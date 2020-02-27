package com.julioluis.trainingrest.repositories;

import com.julioluis.trainingrest.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Integer> {

    @Query(value = "SELECT * FROM session WHERE status=1",nativeQuery = true)
    List<Session> findAll();
    Optional<Session> findById(Integer id);
}
