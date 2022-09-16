package com.job.tracking.service.exception;

import com.job.tracking.model.enums.ErrorType;

public class UserAlreadyExistsException extends ServiceException {

    private static String DEFAULT_MESSAGE = "User already exists";

    public UserAlreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
