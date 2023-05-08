package com.metrodata.clientapp.services;

import com.metrodata.clientapp.models.dto.requests.LoginRequest;
import com.metrodata.clientapp.models.dto.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LoginService {

    private RestTemplate restTemplate;

    @Autowired
    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/login")
    private String url;

    public boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity(loginRequest),
                    new ParameterizedTypeReference<LoginResponse>() {
                    });
            if (response.getStatusCode() == HttpStatus.OK) {
                setPrinciple(response.getBody(), loginRequest.getPassword());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public void setPrinciple(LoginResponse res, String password) {
        Collection<GrantedAuthority> authorities = res.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(res.getUsername(),
                password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

}
