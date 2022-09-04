package com.job.tracking.controller;

import com.job.tracking.controller.dto.CreateTaskRequest;
import com.job.tracking.controller.dto.GetTaskResponse;
import com.job.tracking.controller.dto.TaskResponse;
import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.service.mapping.TaskMapper;
import com.job.tracking.service.model.Task;
import com.job.tracking.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping(value = "/tasks")
public class TasksController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public @ResponseBody
    GetTaskResponse getAllTasks() {
        log.info("Get all tasks");

        GetTaskResponse getTaskResponse = new GetTaskResponse();
        List<TaskResponse> taskResponses = new ArrayList<>();
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            taskResponses.add(taskMapper.mapToTaskDto(task));
        }
        getTaskResponse.setTaskList(taskResponses);
        return getTaskResponse;
    }

    //here we can get one task
    @GetMapping("/{taskNumber}")
    public @ResponseBody
    GetTaskResponse getTask(@PathVariable Integer taskNumber) {
        log.info("Get task by task number: " + taskNumber);

        GetTaskResponse getTaskResponse = new GetTaskResponse();
        Task task = taskService.getTask(taskNumber);
        getTaskResponse.setTaskList(List.of(taskMapper.mapToTaskDto(task)));
        return getTaskResponse;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        log.info("New task was created");

        taskService.createTask(taskMapper.mapCreateTaskRequestToTask(createTaskRequest));
    }

    @DeleteMapping
    public void deleteTask(@RequestParam Integer taskNumber) {
        log.info("Task with number: " + taskNumber + " was deleted");

        taskService.deleteTask(taskNumber);
    }

    @PutMapping
    public Task updateTask(@RequestParam Integer taskNumber, @Valid @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("Task with number: " + taskNumber + " was updated");

        return taskService.updateTask(taskNumber, updateTaskRequest);
    }
}
