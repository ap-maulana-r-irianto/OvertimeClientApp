package com.metrodata.clientapp.models;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProject {
    
    private Integer id;

    @NotNull(message = "Invalid Project: Empty Project")
    private Project project;

    @NotNull(message = "Invalid Employee: Empty Employee")
    private Employee employee;
}
