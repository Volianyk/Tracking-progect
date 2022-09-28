package com.job.tracking.service.exception;

import com.job.tracking.model.enums.ErrorType;

public class IncorrectRepeatPasswordException extends ServiceException{
    private static String DEFAULT_MESSAGE = "repeat password does not match to password";

    public IncorrectRepeatPasswordException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}
