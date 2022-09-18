package com.job.tracking.repository;

import com.job.tracking.repository.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
//    @Query("SELECT u FROM User u WHERE u.status = 1")
//    Collection<User> findAllActiveUsers();
    Optional<UserEntity> findByEmail(String email);

}
