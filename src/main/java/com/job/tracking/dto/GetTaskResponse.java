package com.job.tracking.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetTaskResponse {
    private List<TaskResponse> taskList;

    public List<TaskResponse> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskResponse> taskList) {
        this.taskList = taskList;
    }
}
