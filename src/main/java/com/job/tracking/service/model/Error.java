package com.job.tracking.service.model;

import com.job.tracking.service.model.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {
    private String errorMessage;
    private ErrorType errorType;
    private LocalDateTime localDateTime;
}
