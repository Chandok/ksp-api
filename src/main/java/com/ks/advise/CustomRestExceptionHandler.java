package com.ks.advise;

import com.ks.exception.DataNotFoundException;
import com.ks.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidInputException.class})
    public ResponseEntity<Object> handleAll(InvalidInputException ex, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<Object> handleAll(DataNotFoundException ex, WebRequest request) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
