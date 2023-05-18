package com.metrodata.clientapp.models.dto.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReimburseRequest {
    
    private Integer nominal;

    private String description;

    private String file_url;

    private Integer employee_id;

    private Integer type_id;

}
