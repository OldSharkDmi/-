package com.example.baggagetracker.Exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(Long boardingPass) {
        super("Passenger with boarding pass " + boardingPass + " not found.");
    }
}
