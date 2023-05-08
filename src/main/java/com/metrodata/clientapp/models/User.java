package com.metrodata.clientapp.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    @NotBlank(message = "Invalid Username: Empty Username")
    private String username;

    @NotBlank(message = "Invalid Password: Empty Password")
    private String password;

}
