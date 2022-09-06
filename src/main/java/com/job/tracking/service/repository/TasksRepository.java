package com.job.tracking.service.repository;

import com.job.tracking.service.entity.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TasksRepository extends MongoRepository<TaskEntity, String> {
    TaskEntity findByTaskNumber(Integer taskNumber);
    TaskEntity findFirstByOrderByTaskNumberDesc();
}
