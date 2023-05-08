package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.Department;
import com.metrodata.clientapp.models.dto.requests.DepartmentRequest;


@Service
public class DepartmentService {

    private RestTemplate restTemplate;

    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/department")
    private String url;

    public List<Department> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Department>>() {
                }).getBody();
    }

    public Department getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }

    public Department create(DepartmentRequest departmentRequest) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(departmentRequest),
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }

    public Department update(int id, Department department) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(department),
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }

    public Department delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }
}
