package com.job.tracking.service.mapping;

import com.job.tracking.controller.dto.CreateTaskRequest;
import com.job.tracking.controller.dto.PersonDTO;
import com.job.tracking.controller.dto.TaskResponse;
import com.job.tracking.controller.dto.UpdateTaskRequest;
import com.job.tracking.repository.entity.TaskEntity;
import com.job.tracking.model.Person;
import com.job.tracking.model.Task;
import com.job.tracking.repository.TasksRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public abstract class TaskMapper {

    @Autowired
    TasksRepository tasksRepository;

    public abstract Task mapToTask(TaskEntity taskEntity);

    @Mapping(target = "id", ignore = true)
    public abstract TaskEntity mapToTaskEntity(Task task);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "taskNumber", ignore = true)

    public abstract TaskEntity mapToTaskEntity(@MappingTarget TaskEntity originalEntity, UpdateTaskRequest updateTaskRequest);

    public abstract TaskResponse mapToTaskDto(Task task);

    @Mapping(target = "taskNumber", expression = "java(calculateTaskNumber())")
    public abstract Task mapCreateTaskRequestToTask(CreateTaskRequest createTaskRequest);

    Integer calculateTaskNumber() {
        TaskEntity taskEntityFromRepository = tasksRepository.findFirstByOrderByTaskNumberDesc();
        if (taskEntityFromRepository != null) {
            return taskEntityFromRepository.getTaskNumber() + 1;
        } else {
            return 1;
        }
    }

    @Mapping(target = "password", ignore = true)
    public abstract Person mapToPerson(PersonDTO personDTO);
}
