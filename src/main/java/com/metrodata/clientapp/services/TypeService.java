package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.Type;

@Service
public class TypeService {

    private RestTemplate restTemplate;

    @Autowired
    public TypeService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/type")
    private String url;

    public List<Type> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Type>>() {
                }).getBody();
    }

    public Type getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Type>() {
                }).getBody();
    }

    
    public Type create(Type type) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(type),
                new ParameterizedTypeReference<Type>() {
                }).getBody();
    }

    public Type update(int id, Type type) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(type),
                new ParameterizedTypeReference<Type>() {
                }).getBody();
    }

    public Type delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Type>() {
                }).getBody();
    }
    
}
