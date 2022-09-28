package com.job.tracking.service.exception;

import com.job.tracking.model.enums.ErrorType;

public class UserNotFoundException extends ServiceException{
    private static String DEFAULT_MESSAGE="User is not found";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    @Override
    public ErrorType getErrorType(){
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
