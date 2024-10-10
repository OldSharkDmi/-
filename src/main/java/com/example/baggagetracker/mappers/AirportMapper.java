package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.AirportDTO;
import com.example.baggagetracker.model.Airport;
 public class AirportMapper {

    public static AirportDTO toDTO(Airport airport) {
        AirportDTO dto = new AirportDTO();
        dto.setIATA(airport.getIATA());
        dto.setDepartingPlanes(airport.getDepartingPlanes());
        dto.setArrivingPlanes(airport.getArrivingPlanes());
        dto.setTerminal(airport.getTerminal());
        return dto;
    }

    public static Airport toEntity(AirportDTO dto) {
        Airport airport = new Airport();
        airport.setIATA(dto.getIATA());
        airport.setDepartingPlanes(dto.getDepartingPlanes());
        airport.setArrivingPlanes(dto.getArrivingPlanes());
        airport.setTerminal(dto.getTerminal());
        return airport;
    }
}
