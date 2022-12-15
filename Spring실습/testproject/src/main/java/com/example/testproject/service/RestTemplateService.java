package com.example.testproject.service;

import com.example.testproject.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {

    public String getTestMethod();


    public String getName();

    public String getName2();

    public ResponseEntity<MemberDTO> postDTO();

    public ResponseEntity<MemberDTO> addHeader();

}
