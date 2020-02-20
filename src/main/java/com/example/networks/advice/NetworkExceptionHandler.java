package com.example.networks.advice;

import com.example.networks.exceptions.InvalidRequestException;
import com.example.networks.exceptions.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class NetworkExceptionHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Object> networkNotFoundExceptionHandler(NoSuchElementException noSuchElementException) {
        log.error("Node or network not found");
        return new ResponseEntity<>("Node or network not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<Object> invalidRequestExceptionHandler(InvalidRequestException invalidRequestException) {
        log.error("Invalid request");
        return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
    }
}
