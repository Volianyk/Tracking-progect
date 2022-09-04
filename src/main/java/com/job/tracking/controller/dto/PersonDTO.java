package com.job.tracking.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersonDTO {
    @NotBlank(message = "'name' should not be empty")
    @NotNull
    @NotEmpty(message = "name should not be empty")
    private String name;

    @Email
    @NotEmpty
    private String email;
}
