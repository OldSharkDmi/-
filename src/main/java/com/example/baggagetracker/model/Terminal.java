package com.example.baggagetracker.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Terminal {

    @Id
    private String number;

    @OneToMany(mappedBy = "terminal")
    private List<Airport> airports;

    public List<Airport> getAirports() {
        return airports;
    }

    public String getNumber() {
        return number;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
