package com.metrodata.clientapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    @NotBlank(message = "Invalid Name: Empty Name")
    private String name;

    @NotBlank(message = "Invalid Phone: Empty Phone")
    private String phone;

    @NotBlank(message = "Invalid Email: Empty Email")
    private String email;

    @NotNull(message = "Invalid Department: Empty Department")
    private Department department;
    
}
