package com.eshopping.cart.exception;

import com.eshopping.cart.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleProductNotFound(ResourceNotFoundException ex){
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(ex.getMessage(),NOT_FOUND));
    }

    @ExceptionHandler(ResourceNotSavedException.class)
    public ResponseEntity<ApiResponse> handleNotSavedException(ResourceNotSavedException ex){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(ex.getMessage(),INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ApiResponse("An unexpected Error occurred!. "+ex.getMessage(), INTERNAL_SERVER_ERROR));
    }
}
