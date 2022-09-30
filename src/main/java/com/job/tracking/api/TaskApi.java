package com.job.tracking.api;

import com.job.tracking.controller.dto.CreateTaskRequest;
import com.job.tracking.controller.dto.GetTaskResponse;
import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.model.Task;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/tasks")
@Api(tags = "Task management API")
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})

public interface TaskApi {

    @ApiOperation("Get all tasks")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @ResponseBody
    GetTaskResponse getAllTasks();

    @ApiOperation("Get task by task number")
    @GetMapping("/{taskNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetTaskResponse getTask(@PathVariable Integer taskNumber);

    @ApiOperation("Create task")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest);

    @ApiOperation("Delete task")
    @DeleteMapping
    void deleteTask(@RequestParam Integer taskNumber);

    @ApiOperation("Update task")
    @PutMapping
    Task updateTask(@RequestParam Integer taskNumber, @Valid @RequestBody UpdateTaskRequest updateTaskRequest);

    @ApiOperation("Complete task")
    @PutMapping(value = "complete")
    Task completeTask(@RequestParam Integer taskNumber);

}
