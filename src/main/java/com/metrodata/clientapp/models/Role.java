package com.metrodata.clientapp.models;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer id;

    @NotBlank(message = "Invalid Employee: Empty Employee")
    private String name;
    
}
