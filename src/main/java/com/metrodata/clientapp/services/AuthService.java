package com.metrodata.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.User;
import com.metrodata.clientapp.models.dto.requests.UserRequest;

@Service
public class AuthService {
    
    private RestTemplate restTemplate;

    @Autowired
    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/create")
    private String url;

    public User create(UserRequest userRequest) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(userRequest),
                new ParameterizedTypeReference<User>() {
                }).getBody();
    }

}
