package com.example.testproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("logtest")
    public void logTest(){
        LOGGER.trace("trace log");
        LOGGER.debug("debug log");
        LOGGER.info("info log");
        LOGGER.warn("warn log");
        LOGGER.error("error log");
    }

    @PostMapping("/exception")
    public void exceptionTest() throws Exception{
        throw new Exception();
    }

    @ExceptionHandler(value = Exception.class)
    //ResponsEntity 는 HttpEntity(Http 요청 또는 응답에 해당하는 Httpheader,HttpBody 포함 클래스)
    //를 상속받아 구현. 결과 데이터 및 HTTP 상태 코드를 제어 가능.
    //사용자의 Http Request 에 대한 응답 데이터가 포함된다.
    public ResponseEntity<Map<String,String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info(e.getLocalizedMessage());
        LOGGER.info("Controller 내 ExceptionHandler 호출");

        Map<String,String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message","에러 발생");

        return new ResponseEntity<>(map,responseHeaders,httpStatus);
    }

}
