package com.job.tracking.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PersonDTO {
    @NotBlank(message = "name should not be blank")
    @NotNull(message = "name should not be null")
    @NotEmpty(message = "name should not be empty")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "{email.notempty}")
    private String email;
}
