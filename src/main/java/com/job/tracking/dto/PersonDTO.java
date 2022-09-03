package com.job.tracking.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class PersonDTO {
    @NotBlank(message = "'name' should not be empty")
    private String name;

    @Email
    private String email;
}
