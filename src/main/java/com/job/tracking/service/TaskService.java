package com.job.tracking.service;

import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.service.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTask(Integer taskNumber);

    void createTask(Task task);

    void deleteTask(Integer taskNumber);

    Task updateTask(Integer taskNumber, UpdateTaskRequest updateTaskRequest);
}
