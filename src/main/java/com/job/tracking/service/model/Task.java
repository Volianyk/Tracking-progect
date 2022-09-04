package com.job.tracking.service.model;

import lombok.Data;

@Data
public class Task {
    private String taskDescription;
    private Person creator;
    private Person responsiblePerson;
    private String taskStatus;
    private Integer taskNumber;
}
