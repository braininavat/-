package com.example.testproject.controller;

import com.example.testproject.dto.MemberDTO;
import com.example.testproject.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {
    //RestTemplate이란? http 통신기능을 쓸수있게 설계된 템플릿
    //동기 방식으로 처리되며, AsyncRestTemplate으로 비동기 방식 사용가능
    //Rest 서비스를 호출하도록 설계되어, HTTP 프로토콜의 메소드에 맞게 여러 메소드 제공
    //google.com/robots.txt 사용

    private RestTemplateService restTemplateService;

    @Autowired
    RestTemplateController(RestTemplateService restTemplateService){
        this.restTemplateService = restTemplateService;
    }

    @GetMapping(value = "/test")
    public String testMethod(){
        return restTemplateService.getTestMethod();
    }
    @GetMapping(value = "/testgetname")
    public String testMethod2(){
        return restTemplateService.getName();
    }

    @GetMapping(value = "/testgetname2")
    public String testMethod3(){
        return restTemplateService.getName2();
    }

    @PostMapping(value = "/testpostDTO")
    public String testMethod4(){
       ResponseEntity<MemberDTO> dto = restTemplateService.postDTO();
       return dto.toString();
    }

    @PostMapping(value = "/testaddheader")
    public String testMethod5(){
        ResponseEntity<MemberDTO> dto = restTemplateService.addHeader();
        return dto.toString();
    }
}
