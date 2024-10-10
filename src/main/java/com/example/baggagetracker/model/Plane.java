package com.example.baggagetracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Plane {

    @Id
    private Long flightNumber;

    private String aviaCompany;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    @JsonBackReference
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    @JsonBackReference
    private Airport arrivalAirport;

    @OneToMany(mappedBy = "plane")
    @JsonBackReference
    private List<Passenger> passengers;

    public Plane(Long id) {
        //this.id = id;
    }

    public Plane() {

    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setAviaCompany(String aviaCompany) {
        this.aviaCompany = aviaCompany;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public String getAviaCompany() {
        return aviaCompany;
    }
}
