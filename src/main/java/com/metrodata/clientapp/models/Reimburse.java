package com.metrodata.clientapp.models;

import java.time.LocalDateTime;

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

    private String description;

    private LocalDateTime date_time;
    
    private String file_url;

    private Status status;

    private Employee employee;

    private Type type;
    
}
