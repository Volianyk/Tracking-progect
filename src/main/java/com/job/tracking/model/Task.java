package com.job.tracking.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Task {
    @NotEmpty
    private String taskDescription;
    private Person creator;
    private Person responsiblePerson;
    @NotEmpty
    @Size(min = 3, max = 100, message = "Please enter description")
    private String taskStatus;
    private Integer taskNumber;
}
