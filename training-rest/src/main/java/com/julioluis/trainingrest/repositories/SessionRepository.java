package com.julioluis.trainingrest.repositories;

import com.julioluis.trainingrest.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Integer> {

    @Query(value = "SELECT s FROM Session s WHERE s.status=1",nativeQuery = false)
    List<Session> findAll();
    Optional<Session> findById(Integer id);
    @Query(value = "SELECT * FROM session order by session_id desc limit 1",nativeQuery = true)
    Session findLastSession();
}
