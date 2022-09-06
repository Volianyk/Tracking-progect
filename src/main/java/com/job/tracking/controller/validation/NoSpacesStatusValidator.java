package com.job.tracking.controller.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoSpacesStatusValidator implements ConstraintValidator<StatusDescription, String> {
    @Override
    public void initialize(StatusDescription taskNumber) {
    }

    @Override
    public boolean isValid(String taskDescription, ConstraintValidatorContext cxt) {
        return taskDescription.split(" ").length==1;
    }
}
