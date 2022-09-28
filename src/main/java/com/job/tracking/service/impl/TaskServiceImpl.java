package com.job.tracking.service.impl;

import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.model.enums.TaskStatus;
import com.job.tracking.repository.BillRepository;
import com.job.tracking.repository.entity.Bill;
import com.job.tracking.repository.entity.TaskEntity;
import com.job.tracking.service.TaskService;
import com.job.tracking.service.exception.TaskNotFoundException;
import com.job.tracking.service.mapping.TaskMapper;
import com.job.tracking.model.Task;
import com.job.tracking.repository.TasksRepository;
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

    @Autowired
    private BillRepository billRepository;

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
        if (getOneTask == null) {
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
        if (taskToDelete == null) {
            throw new TaskNotFoundException();
        }
        tasksRepository.delete(taskToDelete);
    }

    public Task updateTask(Integer taskNumber, UpdateTaskRequest updateTaskRequest) {
        TaskEntity taskToUpdate = tasksRepository.findByTaskNumber(taskNumber);
        if (taskToUpdate == null) {
            throw new TaskNotFoundException();
        }
        TaskEntity updatedTask = taskMapper.mapToTaskEntity(taskToUpdate, updateTaskRequest);
        tasksRepository.save(updatedTask);
        return taskMapper.mapToTask(updatedTask);
    }

    public Task completeTask(Integer taskNumber) {
        TaskEntity task = tasksRepository.findByTaskNumber(taskNumber);
        if (task == null) {
            throw new TaskNotFoundException();
        }
        task.setTaskStatus(String.valueOf(TaskStatus.APPROVED));
        tasksRepository.save(task);
        Bill bill = new Bill();
        bill.setAmount("1000");
        bill.setRecipient(task.getResponsiblePerson().getName());
        billRepository.save(bill);
        return taskMapper.mapToTask(task);
    }
}
