package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.metrodata.clientapp.models.HistoryReimburse;
import com.metrodata.clientapp.models.dto.requests.HistoryReimburseRequest;

@Service
public class HistoryReimburseService {
    
    private RestTemplate restTemplate;

    @Autowired
    public HistoryReimburseService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/historyreimburse")
    private String url;

    public List<HistoryReimburse> getAll() {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<HistoryReimburse>>() {
                }).getBody();
    }

    public HistoryReimburse getById(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HistoryReimburse>() {
                }).getBody();
    }

    public HistoryReimburse create(HistoryReimburseRequest historyReimburseRequest) {
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity(historyReimburseRequest),
                new ParameterizedTypeReference<HistoryReimburse>() {
                }).getBody();
    }

    public HistoryReimburse update(int id, HistoryReimburse historyReimburse) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.PUT,
                new HttpEntity(historyReimburse),
                new ParameterizedTypeReference<HistoryReimburse>() {
                }).getBody();
    }

    public HistoryReimburse delete(int id) {
        return restTemplate.exchange(
                url + "/" + id,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<HistoryReimburse>() {
                }).getBody();
    }
}
