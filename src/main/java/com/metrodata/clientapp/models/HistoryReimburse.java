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
public class HistoryReimburse {
    
    private Integer id;

    private LocalDateTime date_time;

    private String status;

    private String description;

    private Reimburse reimburse;
}
