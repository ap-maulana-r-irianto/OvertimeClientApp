package com.metrodata.clientapp.models.dto.requests;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OvertimeRequest {

    @NotBlank(message = "Invalid Description: Empty Description")
    private String description;

    @NotBlank(message = "Invalid Start Time: Empty Time")
    private LocalDateTime start_time;

    @NotBlank(message = "Invalid End Time: Empty Time")
    private LocalDateTime end_time;

    @NotBlank(message = "Invalid Status: Empty Status")
    private String status;

    @NotBlank(message = "Invalid Id: Empty Id")
    private Integer employee_project_id;
    
}
