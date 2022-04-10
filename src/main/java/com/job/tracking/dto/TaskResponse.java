package com.job.tracking.dto;

import lombok.Data;

@Data
public class TaskResponse {
    private String taskDescription;
    private PersonDTO creator;
    private PersonDTO responsiblePerson;
    private String taskStatus;
    private Integer taskNumber;
}
