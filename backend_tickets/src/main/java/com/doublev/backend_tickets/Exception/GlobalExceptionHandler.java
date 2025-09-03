package com.doublev.backend_tickets.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<String> handleNotFound(TicketNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
