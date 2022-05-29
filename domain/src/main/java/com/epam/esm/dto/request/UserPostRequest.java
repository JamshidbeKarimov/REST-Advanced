package com.epam.esm.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserPostRequest {
    private String name;
    @Positive(message = "age cannot be 0 or negative")
    private Integer age;
    @NotBlank(message = "username cannot be null of empty")
    private String username;
    @NotBlank(message = "password cannot be null of empty")
    private String password;
}
