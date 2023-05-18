package com.metrodata.clientapp.models;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Overtime {

    private Integer id;

    private String nominal;

    private String description;
    
    private LocalDateTime start_time;

    private LocalDateTime end_time;

    private Status status;
    
    private EmployeeProject employeeProject;
    
}
