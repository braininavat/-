package com.example.testproject.controller;


import com.example.testproject.data.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api") //공통 URL
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    //RequestMapping 어노테이션 사용시 Http method 따로 지정 필요
    public String getHello(){
        return "Hello";
    }

    @GetMapping(value = "/random-number")
    public int getRandomNumber(){

        return (int)(Math.random()*100);
    }

    @GetMapping(value = "/var1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    @GetMapping(value = "/var2/{variable2}")
    public String getVariable2(@PathVariable(value="variable2") String var){
        return var;
    }

    @GetMapping(value = "/request1") //키-값으로 URL 전달됨, 구분 ? 복수 &
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization){
        return name + " " + email + " " + organization;
    }

    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param) {
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map -> {
            //entrySet() : 키-값 모두 필요할 경우 사용
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDTO memberDTO){
        return memberDTO.toString();
    }
}
