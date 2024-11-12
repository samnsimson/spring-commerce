package com.ecommerce.api.utils;

import com.ecommerce.api.utils.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<String> resourceNotFoundException(ResourceNotFoundException ex){
        this.logger.info("Resource not found exception: {}", ex.getMessage());
        return new ApiResponse<>(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
