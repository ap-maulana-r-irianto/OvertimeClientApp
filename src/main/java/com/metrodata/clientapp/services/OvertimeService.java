package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.Overtime;
import com.metrodata.clientapp.models.dto.requests.OvertimeRequest;

@Service
public class OvertimeService {

    private RestTemplate restTemplate;

    @Autowired
    public OvertimeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/overtime")
    private String url;

    public List<Overtime> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Overtime>>() {
                }).getBody();
    }

    public Overtime getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Overtime>() {
                }).getBody();
    }

    public Overtime create(OvertimeRequest overtimeRequest) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(overtimeRequest),
                new ParameterizedTypeReference<Overtime>() {
                }).getBody();
    }

    public Overtime update(int id, Overtime overtime) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(overtime),
                new ParameterizedTypeReference<Overtime>() {
                }).getBody();
    }

    public Overtime delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Overtime>() {
                }).getBody();
    }
    
}
