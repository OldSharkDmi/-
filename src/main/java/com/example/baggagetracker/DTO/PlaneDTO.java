package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Airport;
import com.example.baggagetracker.model.Passenger;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.transaction.UserTransaction;

import java.util.List;

public class PlaneDTO {
    private Long flightNumber;

    private String aviaCompany;

    private Airport departureAirport;

    private Airport arrivalAirport;

    private List<Passenger> passengers;


    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setAviaCompany(String aviaCompany) {
        this.aviaCompany = aviaCompany;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String getAviaCompany() {
        return aviaCompany;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
}
