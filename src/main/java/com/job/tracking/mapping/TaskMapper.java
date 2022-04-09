package com.job.tracking.mapping;

import com.job.tracking.dto.CreateTaskRequest;
import com.job.tracking.dto.TaskResponse;
import com.job.tracking.dto.UpdateTaskRequest;
import com.job.tracking.entity.TaskEntity;
import com.job.tracking.model.Task;
import org.springframework.stereotype.Component;

@Component
public  class TaskMapper {
    // to user
    public Task mapToTask(TaskEntity taskEntity) {
        Task task = new Task();
        task.setCreator(taskEntity.getCreator());
        task.setTaskDescription(taskEntity.getTaskDescription());
        task.setTaskNumber(taskEntity.getTaskNumber());
        task.setTaskStatus(taskEntity.getTaskStatus());
        task.setResponsiblePerson(taskEntity.getResponsiblePerson());
        return task;
    }
    public TaskResponse mapToTaskDto(Task task) {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setCreator(task.getCreator());
        taskResponse.setTaskDescription(task.getTaskDescription());
        taskResponse.setTaskNumber(task.getTaskNumber());
        taskResponse.setTaskStatus(task.getTaskStatus());
        taskResponse.setResponsiblePerson(task.getResponsiblePerson());
        return taskResponse;
    }

    // from user to bd
    public TaskEntity mapToTaskEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCreator(task.getCreator());
        taskEntity.setTaskDescription(task.getTaskDescription());
        taskEntity.setTaskStatus(task.getTaskStatus());
        taskEntity.setResponsiblePerson(task.getResponsiblePerson());
        return taskEntity;
    }

    public TaskEntity mapToTaskEntity(TaskEntity originalEntity, UpdateTaskRequest updateTaskRequest) {
        originalEntity.setTaskDescription(updateTaskRequest.getTaskDescription());
        originalEntity.setTaskStatus(updateTaskRequest.getTaskStatus());
        originalEntity.setResponsiblePerson(updateTaskRequest.getResponsiblePerson());
        return originalEntity;
    }

    public Task mapCreateTaskRequestToTask(CreateTaskRequest createTaskRequest) {
        Task task = new Task();
        task.setCreator(createTaskRequest.getCreator());
        task.setTaskDescription(createTaskRequest.getTaskDescription());
        task.setTaskStatus(createTaskRequest.getTaskStatus());
        task.setResponsiblePerson(createTaskRequest.getResponsiblePerson());
        return task;
    }
}
