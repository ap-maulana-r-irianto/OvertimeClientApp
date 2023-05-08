package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.Reimburse;
import com.metrodata.clientapp.models.dto.requests.ReimburseRequest;

@Service
public class ReimburseService {

    private RestTemplate restTemplate;

    @Autowired
    public ReimburseService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/reimburse")
    private String url;
    
    public List<Reimburse> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Reimburse>>() {
                }).getBody();
    }

    public Reimburse getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse create(ReimburseRequest reimburseRequest) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(reimburseRequest),
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse update(int id, Reimburse reimburse) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(reimburse),
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    
}
