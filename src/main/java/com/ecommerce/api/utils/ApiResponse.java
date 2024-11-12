package com.ecommerce.api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ApiResponse<T> {
    private String status;
    private HttpStatus statsCode;
    private T data = null;

    public ApiResponse(HttpStatus statusCode, T data){
        this.status = statusCode.is2xxSuccessful() ? "Success" : "Failure";
        this.statsCode = statusCode;
        this.data = data;
    }
}
