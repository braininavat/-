package com.example.testproject.common.exception;

import com.example.testproject.common.Constants;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception{

    private static final long serialVersionUID = 0;
    //직렬화를 위해 사용.

    private Constants.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public CustomException(Constants.ExceptionClass exceptionClass, HttpStatus httpStatus, String message){
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constants.ExceptionClass getExceptionClass(){
        return exceptionClass;
    }

    public int getHttpStatusCode(){ return httpStatus.value();}

    public HttpStatus getHttpStatus(){ return httpStatus;}

    public String getHttpStatusType(){ return httpStatus.getReasonPhrase();}

}
