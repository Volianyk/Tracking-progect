package com.job.tracking.service.exception;

import com.job.tracking.model.enums.ErrorType;

public class TaskNotFoundException extends ServiceException{
    private static String DEFAULT_MESSAGE="Task is not found";

    public TaskNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    @Override
    public ErrorType getErrorType(){
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
