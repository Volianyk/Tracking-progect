package com.job.tracking.service.exception;

import com.job.tracking.service.model.enums.ErrorType;

public class DeletedTaskException extends IllegalArgumentException{
    private static String DEFAULT_MESSAGE="Task does not exist";

    public DeletedTaskException() {
        super(DEFAULT_MESSAGE);
    }

    public ErrorType getErrorType(){
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
