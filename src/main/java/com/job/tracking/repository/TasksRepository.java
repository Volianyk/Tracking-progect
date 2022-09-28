package com.job.tracking.repository;

import com.job.tracking.repository.entity.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TasksRepository extends MongoRepository<TaskEntity, String> {
    TaskEntity findByTaskNumber(Integer taskNumber);
    TaskEntity findFirstByOrderByTaskNumberDesc();
}
