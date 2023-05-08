package com.metrodata.clientapp.models.dto.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Invalid Name: Empty name")
    private String name;

    @NotBlank(message = "Invalid Email: Empty email")
    private String email;

    @NotBlank(message = "Invalid Phone: Empty phone")
    private String phone;

    @NotBlank(message = "Invalid Username: Empty username")
    private String username;

    @NotBlank(message = "Invalid Password: Empty Password")
    private String password;

    @NotBlank(message = "Invalid Department ID: Empty Department ID")
    private Integer departmentId;
}
