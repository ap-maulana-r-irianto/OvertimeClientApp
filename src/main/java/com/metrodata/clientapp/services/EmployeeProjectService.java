package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.EmployeeProject;

@Service
public class EmployeeProjectService {

    private RestTemplate restTemplate;

    @Autowired
    public EmployeeProjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/employeeproject")
    private String url;

    public List<EmployeeProject> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<EmployeeProject>>() {
                }).getBody();
    }

    public EmployeeProject getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<EmployeeProject>() {
                }).getBody();
    }

    public EmployeeProject create(EmployeeProject employeeProject) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(employeeProject),
                new ParameterizedTypeReference<EmployeeProject>() {
                }).getBody();
    }

    public EmployeeProject update(int id, EmployeeProject employeeProject) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(employeeProject),
                new ParameterizedTypeReference<EmployeeProject>() {
                }).getBody();
    }

    public EmployeeProject delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<EmployeeProject>() {
                }).getBody();
    }
    
}

