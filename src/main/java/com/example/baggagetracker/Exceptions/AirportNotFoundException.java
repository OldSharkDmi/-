package com.example.baggagetracker.Exceptions;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String IATA) {
        super("Could not find airport with IATA: " + IATA);
    }
}
