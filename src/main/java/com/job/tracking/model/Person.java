package com.job.tracking.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Person {
    @NotBlank(message = "'name' should not be empty")
    @NotEmpty
    @NotNull
    private String name;

    @Email
    private String email;
    //private String password;
}
