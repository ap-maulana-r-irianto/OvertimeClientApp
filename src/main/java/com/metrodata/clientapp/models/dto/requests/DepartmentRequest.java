package com.metrodata.clientapp.models.dto.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {

    @NotBlank(message = "Invalid Name: Empty Name")
    private String name;

    @NotBlank(message = "Invalid Manager Id: Empty Manager Id")
    private Integer manager_id;

    @NotBlank(message = "Invalid HR Id: Empty HR Id")
    private Integer hr_id;
    
}
