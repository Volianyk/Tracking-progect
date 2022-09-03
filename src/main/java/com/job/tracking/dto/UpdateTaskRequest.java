package com.job.tracking.dto;

import com.job.tracking.model.Person;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UpdateTaskRequest {
    @NotEmpty(message = "Please enter description")
    private String taskDescription;

    private Person responsiblePerson;

    @NotEmpty(message = "Please enter status")
    @Size(min = 4, max = 30)
    private String taskStatus;
}
