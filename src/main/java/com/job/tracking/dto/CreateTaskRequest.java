package com.job.tracking.dto;

import com.job.tracking.model.Person;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateTaskRequest {
    @NotBlank(message = "Please enter description")
    @Size(min = 5,max = 150, message = "Please enter description")
    private String taskDescription;

    private Person creator;
    private Person responsiblePerson;

    @NotBlank(message = "Please enter status")
    private String taskStatus;
}
