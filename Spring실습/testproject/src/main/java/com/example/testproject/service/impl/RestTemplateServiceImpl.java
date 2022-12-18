package com.example.testproject.service.impl;

import com.example.testproject.data.dto.MemberDTO;
import com.example.testproject.service.RestTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);

    @Override
    public String getTestMethod() {
        URI uri = UriComponentsBuilder.
                fromUriString("https://www.google.com")
                .path("/robots.txt")
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri,String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());
        return responseEntity.getBody();
    }

    @Override
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/v1/get-api/request2")
                .queryParam("name", "johnson")
                .queryParam("email", "johnson@gmail.com")
                .encode()
                .build()
                .toUri();
        //queryParam 메소드는 get 요청을 키-밸류 값으로 전달
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/v1/get-api/var1/{name}")
                .encode()
                .build()
                .expand("johnson")
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDTO> postDTO() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/v1/post-api/member3")
                .queryParam("name", "johnson")
                .queryParam("email", "j@gmail.com")
                .queryParam("organization", "MS")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("james");
        memberDTO.setEmail("ja@gmail.com");
        memberDTO.setOrganization("Google");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.postForEntity(uri, memberDTO,
                MemberDTO.class);
        //postForEntity 는 uri, requestbody 가 될 Object, 리턴받는 response 의 Type 필요

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDTO> addHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/api/v1/post-api/member4")
                .encode()
                .build()
                .toUri();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("james");
        memberDTO.setEmail("ja@google.com");
        memberDTO.setOrganization("google");

        RequestEntity<MemberDTO> requestEntity = RequestEntity
                .post(uri)
                .header("header", "google..")
                .body(memberDTO);
        //RequestEntity 를 post 메소드로 사용하겠다는거임
        //RequestEntity. 를 찍어보면 put,delete http method 존재

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDTO> responseEntity = restTemplate.exchange(requestEntity,
                MemberDTO.class);

        LOGGER.info("status code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}

