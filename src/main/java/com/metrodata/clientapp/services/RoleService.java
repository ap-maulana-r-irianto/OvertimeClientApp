package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.Role;

@Service
public class RoleService {
    
    private RestTemplate restTemplate;

    @Autowired
    public RoleService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/role")
    private String url;

    public List<Role> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {
                }).getBody();
    }

    public Role getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Role>() {
                }).getBody();
    }

    public Role getByName(String name) {
        return restTemplate.exchange(
                url + "/name/" + name,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Role>() {
                }).getBody();
    }

    public Role create(Role role) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(role),
                new ParameterizedTypeReference<Role>() {
                }).getBody();
    }

    public Role update(int id, Role role) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(role),
                new ParameterizedTypeReference<Role>() {
                }).getBody();
    }

    public Role delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Role>() {
                }).getBody();
    }
}
