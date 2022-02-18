package com.job.tracking.controller;

import com.job.tracking.dto.UpdateTaskDTO;
import com.job.tracking.entity.TaskEntity;
import com.job.tracking.mapping.TaskMapper;
import com.job.tracking.model.Task;
import com.job.tracking.model.TaskResponse;
import com.job.tracking.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TasksController {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public @ResponseBody
    TaskResponse getAllTasks() {
        TaskResponse taskResponse = new TaskResponse();
        List<Task> tasks = new ArrayList<>();
        List<TaskEntity> taskEntities = tasksRepository.findAll();
        for (TaskEntity taskEntity : taskEntities) {
            tasks.add(taskMapper.mapToTask(taskEntity));
        }
        taskResponse.setTaskList(tasks);
        return taskResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createTask(@RequestBody Task task) {

        TaskEntity taskEntity = taskMapper.mapToTaskEntity(task);
        taskEntity.setTaskNumber(calculateTaskNumber());
        tasksRepository.save(taskEntity);
    }

    private Integer calculateTaskNumber() {
        return tasksRepository.findFirstByOrderByTaskNumberDesc().getTaskNumber() + 1;
    }

    @DeleteMapping
    public void deleteTask(@RequestParam Integer taskNumber) {
        TaskEntity taskToDelete = tasksRepository.findByTaskNumber(taskNumber);
        tasksRepository.delete(taskToDelete);
    }

    @PutMapping
    public Task updateTask(@RequestParam Integer taskNumber, @RequestBody UpdateTaskDTO updateTaskDTO) {
        TaskEntity taskToUpdate = tasksRepository.findByTaskNumber(taskNumber);
        TaskEntity updatedTask = taskMapper.mapToTaskEntity(taskToUpdate, updateTaskDTO);
        tasksRepository.save(updatedTask);
        return taskMapper.mapToTask(updatedTask);
    }


}

