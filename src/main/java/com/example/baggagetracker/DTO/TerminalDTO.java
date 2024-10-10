package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Airport;
import jakarta.persistence.OneToMany;

import java.util.List;

public class TerminalDTO {
    private String number;
    private List<Airport> airports;

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public String getNumber() {
        return number;
    }

    public List<Airport> getAirports() {
        return airports;
    }
}
