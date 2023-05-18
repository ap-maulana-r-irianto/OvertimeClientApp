package com.metrodata.clientapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.metrodata.clientapp.models.Reimburse;
import com.metrodata.clientapp.models.dto.requests.ReimburseRequest;

@Service
public class ReimburseService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileStorageService fileStorageService;

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

    public Reimburse create(ReimburseRequest reimburseRequest, MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        reimburseRequest.setFile_url(fileDownloadUri);
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

    public Reimburse approvManager(int id) {
        return restTemplate.exchange(
                url + "/approv/manager/" + id,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse rejectManager(int id, String description) {
        return restTemplate.exchange(
                url + "/reject/manager/" + id,
                HttpMethod.PUT,
                new HttpEntity(description),
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse approvHr(int id) {
        return restTemplate.exchange(
                url + "/approv/hr/" + id,
                HttpMethod.PUT,
                null,
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse rejectHr(int id, String description) {
        return restTemplate.exchange(
                url + "/reject/hr/" + id,
                HttpMethod.PUT,
                new HttpEntity(description),
                new ParameterizedTypeReference<Reimburse>() {
                }).getBody();
    }

    public Reimburse paid(int id) {
        return restTemplate.exchange(
                url + "/paid/" + id,
                HttpMethod.PUT,
                null,
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
