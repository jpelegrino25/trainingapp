package com.julioluis.trainingrest.repositories;

import com.julioluis.trainingrest.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);
    @Query(value = "SELECT * from user WHERE status=1", nativeQuery = true)
    List<User> getAllActiveUsers();
}
