package com.julioluis.trainingrest.repositories;

import com.julioluis.trainingrest.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPagingRepository extends PagingAndSortingRepository<User,Integer> {
}
