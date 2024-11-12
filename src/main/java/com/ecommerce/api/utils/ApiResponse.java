package com.ecommerce.api.utils;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Data
class FormatedResponse<T>{
    private Integer statusCode;
    private String status;
    private T data;
    private Map<String, String> errors;

    public FormatedResponse(HttpStatus statusCode, T data){
        this.status = statusCode.is2xxSuccessful() ? "Success" : "Failure";
        this.statusCode = statusCode.value();
        this.data = data;
    }

    public FormatedResponse(HttpStatus statusCode, T data, Map<String, String> errors){
        this.status = statusCode.is2xxSuccessful() ? "Success" : "Failure";
        this.statusCode = statusCode.value();
        this.data = data;
        this.errors = errors;
    }
}

public class ApiResponse<T> extends ResponseEntity<FormatedResponse<T>> {
    public ApiResponse(HttpStatus statusCode, T data){
        super(new FormatedResponse<>(statusCode, data), statusCode);
    }
    public ApiResponse(HttpStatus statusCode, T data, Map<String, String> errors) {
        super(new FormatedResponse<>(statusCode, data, errors), statusCode);
    }
}
