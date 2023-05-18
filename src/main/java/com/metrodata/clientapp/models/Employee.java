package com.metrodata.clientapp.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String account_bank;

    private Integer payroll;

    private Department department;

    private Employee manager;

    private User user;
    
}
