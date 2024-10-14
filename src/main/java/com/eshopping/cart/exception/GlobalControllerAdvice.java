package com.eshopping.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleProductNotFound(ResourceNotFoundException ex){
        return new ResponseEntity<>("Error: "+ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceNotSavedException.class)
    public ResponseEntity<String> handleNotSavedException(ResourceNotSavedException ex){
        return new ResponseEntity<>("Error: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> handleException(Exception ex){
        return new ResponseEntity<>("Error: An unexpected Error occurred!.."+ex.getMessage(), HttpStatus.BAD_GATEWAY);
    }
}
