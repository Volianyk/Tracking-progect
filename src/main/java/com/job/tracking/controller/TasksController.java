package com.job.tracking.controller;

import com.job.tracking.controller.dto.CreateTaskRequest;
import com.job.tracking.controller.dto.GetTaskResponse;
import com.job.tracking.controller.dto.TaskResponse;
import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.service.TaskService;
import com.job.tracking.service.mapping.TaskMapper;
import com.job.tracking.service.model.Task;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Api(tags = "Task management API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})

public class TasksController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @ApiOperation("Get all tasks")
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
    @ApiOperation("Get task by task number")
    @GetMapping("/{taskNumber}")
    public @ResponseBody
    GetTaskResponse getTask(@PathVariable Integer taskNumber) {
        log.info("Get task by task number: " + taskNumber);

        GetTaskResponse getTaskResponse = new GetTaskResponse();
        Task task = taskService.getTask(taskNumber);
        getTaskResponse.setTaskList(List.of(taskMapper.mapToTaskDto(task)));
        return getTaskResponse;
    }

    @ApiOperation("Create task")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        log.info("New task was created");

        taskService.createTask(taskMapper.mapCreateTaskRequestToTask(createTaskRequest));
    }

    @ApiOperation("Delete task")
    @DeleteMapping
    public void deleteTask(@RequestParam Integer taskNumber) {
        log.info("Task with number: " + taskNumber + " was deleted");

        taskService.deleteTask(taskNumber);
    }

    @ApiOperation("Update task")
    @PutMapping
    public Task updateTask(@RequestParam Integer taskNumber, @Valid @RequestBody UpdateTaskRequest updateTaskRequest) {
        log.info("Task with number: " + taskNumber + " was updated");

        return taskService.updateTask(taskNumber, updateTaskRequest);
    }
}
