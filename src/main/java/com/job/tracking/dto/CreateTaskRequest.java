package com.job.tracking.dto;

import com.job.tracking.model.Person;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class CreateTaskRequest {
    @NotEmpty (message = "")
    private String taskDescription;
    @Valid
    private Person creator;
    private Person responsiblePerson;
    @NotBlank
    private String taskStatus;
}
