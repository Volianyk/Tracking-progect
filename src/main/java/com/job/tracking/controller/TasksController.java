package com.job.tracking.controller;

import com.job.tracking.api.TaskApi;
import com.job.tracking.controller.dto.CreateTaskRequest;
import com.job.tracking.controller.dto.GetTaskResponse;
import com.job.tracking.controller.dto.TaskResponse;
import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.model.Task;
import com.job.tracking.service.TaskService;
import com.job.tracking.service.mapping.TaskMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

public class TasksController implements TaskApi {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TaskService taskService;

    @Override
    public GetTaskResponse getAllTasks() {

        GetTaskResponse getTaskResponse = new GetTaskResponse();
        List<TaskResponse> taskResponses = new ArrayList<>();
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            taskResponses.add(taskMapper.mapToTaskDto(task));
        }
        getTaskResponse.setTaskList(taskResponses);
        return getTaskResponse;
    }

    @Override
    public GetTaskResponse getTask(Integer taskNumber) {

        GetTaskResponse getTaskResponse = new GetTaskResponse();
        Task task = taskService.getTask(taskNumber);
        getTaskResponse.setTaskList(List.of(taskMapper.mapToTaskDto(task)));
        return getTaskResponse;
    }

    @Override
    public void createTask(CreateTaskRequest createTaskRequest) {
        taskService.createTask(taskMapper.mapCreateTaskRequestToTask(createTaskRequest));
    }

    @Override
    public void deleteTask(Integer taskNumber) {
        taskService.deleteTask(taskNumber);
    }

    @Override
    public Task updateTask(Integer taskNumber, UpdateTaskRequest updateTaskRequest) {
        return taskService.updateTask(taskNumber, updateTaskRequest);
    }

    @Override
    public Task completeTask(Integer taskNumber) {
        return taskService.completeTask(taskNumber);
    }
}
