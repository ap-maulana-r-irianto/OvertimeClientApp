package com.metrodata.clientapp.models.dto.requests;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;

    private String phone;
    
    private String email;
    
    private String account_bank;
    
    private String username;
    
    private String password;
    
    private Integer role_id;
    
    private Integer department_id;
    
    private Integer manager_id;
}
