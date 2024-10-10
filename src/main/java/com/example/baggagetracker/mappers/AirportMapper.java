package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.AirportDTO;
import com.example.baggagetracker.model.Airport;
import com.example.baggagetracker.model.Plane;
import com.example.baggagetracker.model.Terminal;

import java.util.stream.Collectors;

public class AirportMapper {

    public static AirportDTO toDTO(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setIATA(airport.getIATA());

        dto.setDepartingPlanes(airport.getDepartingPlanes().stream()
                .map(Plane::getFlightNumber)
                .collect(Collectors.toList()));

        dto.setArrivingPlanes(airport.getArrivingPlanes().stream()
                .map(Plane::getFlightNumber)
                .collect(Collectors.toList()));

        dto.setTerminalId(airport.getTerminal().getId());

        return dto;
    }

    public static Airport toEntity(AirportDTO dto) {
        Airport airport = new Airport();
        airport.setIATA(dto.getIATA());

        airport.setDepartingPlanes(dto.getDepartingPlanes().stream()
                .map(id -> new Plane(id))
                .collect(Collectors.toList()));

        airport.setArrivingPlanes(dto.getArrivingPlanes().stream()
                .map(id -> new Plane(id))
                .collect(Collectors.toList()));


        Terminal terminal = new Terminal();
        terminal.setId(dto.getTerminalId());
        airport.setTerminal(terminal);

        return airport;
    }
}
