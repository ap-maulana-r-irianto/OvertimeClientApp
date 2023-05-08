package com.metrodata.clientapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reimburse {

    private Integer id;
    private int nominal;

    @NotBlank(message = "Invalid Description: Empty Description")
    private String description;

    @NotBlank(message = "Invalid File: Empty File")
    private String file;

    @NotBlank(message = "Invalid Status: Empty Status")
    private String status;

    @NotNull(message = "Invalid Employee: Empty Employee")
    private Employee employee;

    @NotNull(message = "Invalid Type: Empty Type")
    private Type type;
    
}
