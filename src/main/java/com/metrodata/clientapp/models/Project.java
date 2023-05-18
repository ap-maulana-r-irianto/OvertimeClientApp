package com.metrodata.clientapp.models;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    
    private Integer id;

    private String name;

    private String description;

    private LocalDate start_date;

    private LocalDate end_date;

    private int budget;
   
    private boolean status;

}
