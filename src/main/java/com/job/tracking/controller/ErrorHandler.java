package com.job.tracking.controller;

import com.job.tracking.service.exception.DeletedTaskException;
import com.job.tracking.service.exception.ServiceException;
import com.job.tracking.service.exception.TaskNotFoundException;
import com.job.tracking.service.model.Error;
import com.job.tracking.service.model.enums.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("handleMethodArgumentNotValidException: exception {}", ex.getMessage(), ex);
        return ex.getBindingResult().getAllErrors().stream()
                .map(err -> new Error(
                        err.getDefaultMessage(), ErrorType.VALIDATION_ERROR_TYPE, LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleEntityNotFoundException(ServiceException ex, HandlerMethod hm) {
        log.error("handleEntityNotFoundException: exception {}, method: {}", ex.getMessage(),
                hm.getMethod().getName(), ex);
        return new Error(ex.getMessage(), ex.getErrorType(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleException(ServiceException ex, HandlerMethod hm) {
        log.error("handleException: exception {}, method: {}", ex.getMessage(),
                hm.getMethod().getName(), ex);
        return new Error(ex.getMessage(), ErrorType.FATAL_ERROR_TYPE, LocalDateTime.now());

    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleTaskNotFoundException(ServiceException ex, HandlerMethod hm) {
        log.error("handleTaskNotFoundException: exception {}, method: {}", ex.getMessage(),
                hm.getMethod().getName(), ex);
        return new Error(ex.getMessage(), ErrorType.TASK_NOT_FOUND, LocalDateTime.now());

    }

    @ExceptionHandler(DeletedTaskException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleDeletedException(IllegalArgumentException ex, HandlerMethod hm) {
        log.error("handleDeletedException: exception {}, method: {}", ex.getMessage(),
                hm.getMethod().getName(), ex);
        return new Error(ex.getMessage(), ErrorType.TASK_DOES_NOT_EXIST, LocalDateTime.now());

    }
}
