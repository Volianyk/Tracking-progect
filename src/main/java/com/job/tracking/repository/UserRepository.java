package com.job.tracking.repository;

import com.job.tracking.repository.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

//    @Query("SELECT*")
//    List<UserEntity> getAllUsers();

}
