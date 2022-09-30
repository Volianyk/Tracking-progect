package com.job.tracking.controller.dto;

import com.job.tracking.controller.validation.StatusDescription;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateTaskRequest {
    @Size(min = 5, max = 150, message = "Please enter description for 5 to 150 symbols")
    @NotEmpty(message = "Description should not be empty")
    private String taskDescription;

    @Valid
    private PersonDTO creator;

    @Valid
    private PersonDTO responsiblePerson;

    @StatusDescription
    private String taskStatus;
}
