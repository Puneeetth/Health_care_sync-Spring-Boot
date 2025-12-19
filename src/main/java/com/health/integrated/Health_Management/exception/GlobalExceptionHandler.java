package com.health.integrated.Health_Management.exception;

import com.health.integrated.Health_Management.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        null
                ), HttpStatus.NOT_FOUND

        );
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequest(BadRequestException ex){
        return new ResponseEntity<>(new ApiResponse<>(false,ex.getMessage(),null),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex){
            return new ResponseEntity<>(
                    new ApiResponse<>(false,ex.getMessage(),null),HttpStatus.INTERNAL_SERVER_ERROR
            );
    }
}
