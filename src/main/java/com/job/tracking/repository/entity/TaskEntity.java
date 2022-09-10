package com.job.tracking.repository.entity;

import com.job.tracking.model.Person;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tasks")
@Data
public class TaskEntity {

    @Id
    private String id;
    private String taskDescription;
    private Person creator;
    private Person responsiblePerson;
    private String taskStatus;
    private Integer taskNumber;

}
