package com.example.baggagetracker.graphql;


import com.example.baggagetracker.Repository.AirportRepository;
import com.example.baggagetracker.Repository.BaggageClaimRepository;
import com.example.baggagetracker.Repository.PassengerRepository;
import com.example.baggagetracker.Repository.PlaneRepository;
import com.example.baggagetracker.model.Airport;
import com.example.baggagetracker.model.BaggageClaim;
import com.example.baggagetracker.model.Passenger;
import com.example.baggagetracker.model.Plane;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QueryResolver {

    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;

    public QueryResolver(AirportRepository airportRepository, PlaneRepository planeRepository) {
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
    }

    @QueryMapping
    public List<Airport> airports() {
        return airportRepository.findAll();
    }

    @QueryMapping
    public List<Plane> planes() {
        return planeRepository.findAll();
    }
}