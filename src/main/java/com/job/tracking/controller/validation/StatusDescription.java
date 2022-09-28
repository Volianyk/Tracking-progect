package com.job.tracking.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NoSpacesStatusValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusDescription {
    String message() default "Enter status for the task";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
