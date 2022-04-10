package com.job.tracking.dto;

import com.job.tracking.model.Person;
import lombok.Data;

@Data
public class CreateTaskRequest {
    private String taskDescription;
    private Person creator;
    private Person responsiblePerson;
    private String taskStatus;
}
