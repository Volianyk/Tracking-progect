package com.job.tracking.mapping;

import com.job.tracking.entity.TaskEntity;
import com.job.tracking.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
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

    // from user to bd
    public TaskEntity mapToTaskEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setCreator(task.getCreator());
        taskEntity.setTaskDescription(task.getTaskDescription());
        taskEntity.setTaskNumber(task.getTaskNumber());
        taskEntity.setTaskStatus(task.getTaskStatus());
        taskEntity.setResponsiblePerson(task.getResponsiblePerson());
        return taskEntity;
    }

}
