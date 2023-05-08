package com.metrodata.clientapp.models.dto.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Invalid Username: Empty username")
    private String username;

    @NotBlank(message = "Invalid Password: Empty password")
    private String password;

}
