package com.example.baggagetracker;

import com.example.baggagetracker.Repository.*;
import com.example.baggagetracker.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BaggagetrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaggagetrackerApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(AirportRepository airportRepository, PlaneRepository planeRepository, TerminalRepository terminalRepository, PassengerRepository passengerRepository, BaggageRepository baggageRepository) {
        return (args) -> {

            Terminal terminal1 = new Terminal();
            terminal1.setId("T1"); //
            terminal1.setNumber("Terminal 1");
            terminalRepository.save(terminal1);


            Airport jfkAirport = new Airport();
            jfkAirport.setIATA("JFK");
            jfkAirport.setTerminal(terminal1);
            airportRepository.save(jfkAirport);


            Airport laxAirport = new Airport();
            laxAirport.setIATA("LAX");
            laxAirport.setTerminal(terminal1);
            airportRepository.save(laxAirport);


            Plane plane1 = new Plane();
            plane1.setFlightNumber(1234L);
            plane1.setAviaCompany("Airline ABC");
            plane1.setDepartureAirport(jfkAirport);
            plane1.setArrivalAirport(laxAirport);
            planeRepository.save(plane1);

            Plane plane2 = new Plane();
            plane2.setFlightNumber(5678L);
            plane2.setAviaCompany("Airline XYZ");
            plane2.setDepartureAirport(laxAirport);
            plane2.setArrivalAirport(jfkAirport);
            planeRepository.save(plane2);

            Passenger passenger1 = new Passenger();
            passenger1.setBoardingPass(1001L);
            passenger1.setName("John");
            passenger1.setSurname("Doe");
            passenger1.setPassport("A12345678");
            passenger1.setPhone("+1234567890");
            passenger1.setPlane(plane1);
            passengerRepository.save(passenger1);

            Passenger passenger2 = new Passenger();
            passenger2.setBoardingPass(1002L);
            passenger2.setName("Jane");
            passenger2.setSurname("Smith");
            passenger2.setPassport("B87654321");
            passenger2.setPhone("+0987654321");
            passenger2.setPlane(plane2);
            passengerRepository.save(passenger2);


            Baggage baggage1 = new Baggage();
            baggage1.setWeight(23.5);
            baggage1.setInspectionStatus("Cleared");
            baggage1.setPassenger(passenger1);
            baggageRepository.save(baggage1);

            Baggage baggage2 = new Baggage();
            baggage2.setWeight(18.0);
            baggage2.setInspectionStatus("Cleared");
            baggage2.setPassenger(passenger2);
            baggageRepository.save(baggage2);


            jfkAirport.setDepartingPlanes(List.of(plane1));
            jfkAirport.setArrivingPlanes(List.of(plane2));
            laxAirport.setDepartingPlanes(List.of(plane2));
            laxAirport.setArrivingPlanes(List.of(plane1));
            airportRepository.save(jfkAirport);
            airportRepository.save(laxAirport);
        };
    }



}
