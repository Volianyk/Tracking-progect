package com.job.tracking.dto;

import com.job.tracking.model.Person;
import lombok.Data;

@Data
public class UpdateTaskRequest {
    private String taskDescription;
    private Person responsiblePerson;
    private String taskStatus;

}
