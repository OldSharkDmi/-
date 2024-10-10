package com.example.baggagetracker.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BaggageNotFoundException extends RuntimeException {
    public BaggageNotFoundException(Long id) {
        super("Baggage with ID " + id + " not found.");
    }
}
