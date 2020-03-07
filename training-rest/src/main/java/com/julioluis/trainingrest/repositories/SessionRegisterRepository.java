package com.julioluis.trainingrest.repositories;

import com.julioluis.trainingrest.entities.SessionRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRegisterRepository extends JpaRepository<SessionRegister,Integer> {

    @Query(value = "SELECT rs FROM SessionRegister rs WHERE rs.registerSessionId.user.id=:userId AND rs.status.id=3")
    List<SessionRegister> findSessionRegisterByUser(@Param(value = "userId") Integer userId);
}
