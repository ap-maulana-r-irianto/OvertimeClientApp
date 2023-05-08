package com.metrodata.clientapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Integer id;

    @NotBlank(message = "Invalid Name: Empty Name")
    private String name;

    @NotNull(message = "Invalid Manager: Empty Manager")
    private Employee manager;

    @NotNull(message = "Invalid Hr: Empty Hr")
    private Employee hr;
    
}
