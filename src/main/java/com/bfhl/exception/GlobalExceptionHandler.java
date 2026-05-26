package com.bfhl.exception;

import com.bfhl.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleAllExceptions(Exception ex) {
        BfhlResponse response = new BfhlResponse();
        response.setSuccess(false);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
