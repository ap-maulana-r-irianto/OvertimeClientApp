package com.metrodata.clientapp.models;

import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String username;

    private String password;

    private Boolean isEnabled = true;

    private Boolean isAccountNonLocked = true;

    private List<Role> role;

    public boolean hasRole(String roleName) {
        Iterator<Role> iterator = getRole().iterator();
        while (iterator.hasNext()) {
            Role role = iterator.next();
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
         
        return false;
    }
}
