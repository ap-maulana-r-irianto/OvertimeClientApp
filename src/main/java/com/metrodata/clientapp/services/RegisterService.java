package com.metrodata.clientapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.dto.requests.UserRequest;

@Service
public class RegisterService {
    
    private RestTemplate restTemplate;

    @Autowired
    public RegisterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/register")
    private String urlRegister;

    @Value("${server.baseUrl}/create")
    private String urlCreate;

    public UserRequest register(UserRequest userRequest) {
        return restTemplate.exchange(
                urlRegister,
                HttpMethod.POST,
                new HttpEntity(userRequest),
                new ParameterizedTypeReference<UserRequest>() {
                }).getBody();
    }

    public UserRequest create(UserRequest userRequest) {
        return restTemplate.exchange(
                urlCreate,
                HttpMethod.POST,
                new HttpEntity(userRequest),
                new ParameterizedTypeReference<UserRequest>() {
                }).getBody();
    }
}
