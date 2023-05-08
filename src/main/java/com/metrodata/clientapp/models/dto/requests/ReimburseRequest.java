package com.metrodata.clientapp.models.dto.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReimburseRequest {

    @NotBlank(message = "Invalid Nominal: Empty Nominal")
    private Integer nominal;

    @NotBlank(message = "Invalid Description: Empty Description")
    private String description;

    @NotBlank(message = "Invalid File: Empty File")
    private String file;

    @NotBlank(message = "Invalid Status: Empty Status")
    private String status;

    @NotBlank(message = "Invalid Employee Id: Empty Employee Id")
    private Integer employee_id;

    @NotBlank(message = "Invalid Type Id: Empty Type Id")
    private Integer type_id;

}
