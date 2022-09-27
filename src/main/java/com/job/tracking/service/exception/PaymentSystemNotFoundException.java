package com.job.tracking.service.exception;

import com.job.tracking.model.enums.ErrorType;

public class PaymentSystemNotFoundException extends ServiceException {
    private static String DEFAULT_MESSAGE = "Payment system is not found";

    public PaymentSystemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.VALIDATION_ERROR_TYPE;
    }
}