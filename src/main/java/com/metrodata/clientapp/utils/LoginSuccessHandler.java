package com.metrodata.clientapp.utils;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.metrodata.clientapp.models.AppUserDetail;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
 
        AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();
         
        String redirectURL = request.getContextPath();
         
        if (userDetails.hasRole("Salesperson")) {
            redirectURL = "sales_home";
        } else if (userDetails.hasRole("Editor")) {
            redirectURL = "editor_home";
        } else if (userDetails.hasRole("Shipper")) {
            redirectURL = "shipper_home";
        }
         
        response.sendRedirect(redirectURL);
         
    }
 
}