package com.example.baggagetracker;

import com.example.baggagetracker.Repository.AirportRepository;
import com.example.baggagetracker.Repository.PlaneRepository;
import com.example.baggagetracker.Repository.TerminalRepository;
import com.example.baggagetracker.model.Airport;
import com.example.baggagetracker.model.Plane;
import com.example.baggagetracker.model.Terminal;
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
    public CommandLineRunner loadData(AirportRepository airportRepository, PlaneRepository planeRepository, TerminalRepository terminalRepository) {
        return (args) -> {
            // Создание терминала
            Terminal terminal1 = new Terminal();
            terminal1.setNumber("T1");
            terminalRepository.save(terminal1);

            // Создание аэропорта JFK
            Airport jfkAirport = new Airport();
            jfkAirport.setIATA("JFK");
            jfkAirport.setTerminal(terminal1);
            airportRepository.save(jfkAirport);

            // Создание аэропорта LAX
            Airport laxAirport = new Airport();
            laxAirport.setIATA("LAX");
            laxAirport.setTerminal(terminal1);
            airportRepository.save(laxAirport);

            // Создаем и сохраняем самолеты
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

            // Добавляем самолеты в аэропорты
            jfkAirport.setDepartingPlanes(List.of(plane1));
            jfkAirport.setArrivingPlanes(List.of(plane2));
            laxAirport.setDepartingPlanes(List.of(plane2));
            laxAirport.setArrivingPlanes(List.of(plane1));
            airportRepository.save(jfkAirport);
            airportRepository.save(laxAirport);
        };
    }


}
