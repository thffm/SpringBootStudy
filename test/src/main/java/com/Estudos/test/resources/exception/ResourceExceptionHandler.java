package com.Estudos.test.resources.exception;

import com.Estudos.test.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err =new StandardError();
        err.setTimeStamp(Instant.now());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setStatus(status.value());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
