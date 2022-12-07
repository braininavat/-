package com.example.testproject.controller;


import com.example.testproject.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Member;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {

    @PutMapping(value = "hello")
    public String putHello(){
        return "hello Put";
    }

    @PutMapping(value = "/member")
    public String postMember1(@RequestBody Map<String,Object> putData){
        StringBuilder sb = new StringBuilder();

        putData.entrySet().forEach(map->{
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    @PutMapping(value = "/member2")
    public MemberDTO postMember2(@RequestBody MemberDTO memberDTO){
        return memberDTO;
    }

    @PutMapping(value = "/member3")
    public ResponseEntity<MemberDTO> postMember3(@RequestBody MemberDTO memberDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(memberDTO);
    }

}
