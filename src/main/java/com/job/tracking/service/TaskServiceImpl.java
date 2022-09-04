package com.job.tracking.service;

import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.service.entity.TaskEntity;
import com.job.tracking.service.exception.TaskNotFoundException;
import com.job.tracking.service.mapping.TaskMapper;
import com.job.tracking.service.model.Task;
import com.job.tracking.service.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        List<TaskEntity> taskEntities = tasksRepository.findAll();
        for (TaskEntity taskEntity : taskEntities) {
            tasks.add(taskMapper.mapToTask(taskEntity));
        }
        return tasks;
    }

    public Task getTask(Integer taskNumber) {
        TaskEntity getOneTask = tasksRepository.findByTaskNumber(taskNumber);
        if(getOneTask==null){
            throw new TaskNotFoundException();
        }
        Task task = taskMapper.mapToTask(getOneTask);
        return task;
    }

    public void createTask(Task task) {
        TaskEntity taskEntity = taskMapper.mapToTaskEntity(task);
        tasksRepository.save(taskEntity);
    }

    public void deleteTask(Integer taskNumber) {
        TaskEntity taskToDelete = tasksRepository.findByTaskNumber(taskNumber);
        tasksRepository.delete(taskToDelete);
    }

    public Task updateTask(Integer taskNumber, UpdateTaskRequest updateTaskRequest) {
        TaskEntity taskToUpdate = tasksRepository.findByTaskNumber(taskNumber);
        TaskEntity updatedTask = taskMapper.mapToTaskEntity(taskToUpdate, updateTaskRequest);
        tasksRepository.save(updatedTask);
        return taskMapper.mapToTask(updatedTask);
    }
}
