package com.example.testproject.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//컨트롤러 단에서 발생하는 예외처리 여기서.
//ControllerAdvice 와 ResponseBody(객체 리턴) 를 합친 어노테이션.
//try-catch 는 지저분할수밖에 없는 코드(Clean Code)- Throw 를 사용해도 되지만 어노테이션으로 예외처리 가능해짐
//@Service 의 Bean 에서는 동작하지 않는다.
public class RestExceptionHandler {
    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    //특정 예외 클래스를 지정하면 해당 예외 발생시, 이 메서드가 처리함
    public ResponseEntity<Map<String,String>> ExceptionHandler(Exception e){
        HttpHeaders responseHeaders = new HttpHeaders();
        //responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.info("Advice 내 ExceptionHandler 호출");

        Map<String,String> map = new HashMap<>();
        map.put("error type",httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message","에러 발생");

        return new ResponseEntity<>(map,responseHeaders,httpStatus);
    }

    @ExceptionHandler(value = CustomException.class)
    //특정 예외 클래스를 지정하면 해당 예외 발생시, 이 메서드가 처리함
    public ResponseEntity<Map<String,String>> ExceptionHandler(CustomException e){
        HttpHeaders responseHeaders = new HttpHeaders();

        Map<String,String> map = new HashMap<>();
        map.put("error type",e.getHttpStatusType());
        map.put("code", Integer.toString(e.getHttpStatusCode()));
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map,responseHeaders,e.getHttpStatus());
    }
}
