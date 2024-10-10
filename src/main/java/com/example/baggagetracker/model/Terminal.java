package com.example.baggagetracker.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Terminal {


    @Id
//UPDATE terminal SET id = gen_random_uuid() WHERE id IS NULL;
    private String id;
    private String number;
    //private String id = UUID.randomUUID().toString();
    @OneToMany(mappedBy = "terminal")
    @JsonManagedReference
    private List<Airport> airports;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

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
