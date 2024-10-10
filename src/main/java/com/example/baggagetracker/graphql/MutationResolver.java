package com.example.baggagetracker.graphql;


import com.example.baggagetracker.Repository.AirportRepository;
import com.example.baggagetracker.Repository.PlaneRepository;
import com.example.baggagetracker.model.Airport;
import com.example.baggagetracker.model.Plane;
import com.example.baggagetracker.model.Terminal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;

    public MutationResolver(AirportRepository airportRepository, PlaneRepository planeRepository) {
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
    }

    @MutationMapping
    public Airport addAirport(String IATA, String terminalNumber) {
        Terminal terminal = new Terminal();
        terminal.setNumber(terminalNumber);
        Airport airport = new Airport();
        airport.setIATA(IATA);
        airport.setTerminal(terminal);
        return airportRepository.save(airport);
    }

    @MutationMapping
    public Plane addPlane(Long flightNumber, String aviaCompany, String departureIATA, String arrivalIATA) {
        Airport departureAirport = airportRepository.findById(departureIATA).orElseThrow();
        Airport arrivalAirport = airportRepository.findById(arrivalIATA).orElseThrow();
        Plane plane = new Plane();
        plane.setFlightNumber(flightNumber);
        plane.setAviaCompany(aviaCompany);
        plane.setDepartureAirport(departureAirport);
        plane.setArrivalAirport(arrivalAirport);
        return planeRepository.save(plane);
    }
}
