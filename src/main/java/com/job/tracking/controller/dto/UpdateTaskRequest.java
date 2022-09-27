package com.job.tracking.controller.dto;

import com.job.tracking.controller.validation.StatusDescription;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UpdateTaskRequest {

    @NotEmpty(message = "Please enter description")
    @Size(min = 4, max = 150)
    private String taskDescription;

    @Valid
    private PersonDTO responsiblePerson;

    @NotEmpty(message = "Please enter status")
    @StatusDescription
    private String taskStatus;
}
