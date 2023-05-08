package com.metrodata.clientapp.models.dto.requests;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryReimburseRequest {


    @NotBlank(message = "Date & Time: Empty Date & Time")
    private LocalDateTime date_time;

    @NotBlank(message = "Invalid Status: Empty Status")
    private String status;

    @NotBlank(message = "Invalid Description: Empty Description")
    private String description;

    @NotBlank(message = "Invalid Reimburse Id: Empty Reimburse Id")
    private Integer reimburse_id;
    
}
