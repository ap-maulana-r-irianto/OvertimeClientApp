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

    private Project project;

    private Employee employee;
}
