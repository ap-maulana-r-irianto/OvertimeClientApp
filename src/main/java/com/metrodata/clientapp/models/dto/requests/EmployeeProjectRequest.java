package com.metrodata.clientapp.models.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeProjectRequest {
    
    private Integer employee_id;

    private Integer project_id;
    
}
