package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.PlaneDTO;
import com.example.baggagetracker.model.Plane;

public class PlaneMapper {

    public static PlaneDTO toDTO(Plane plane) {
        PlaneDTO dto = new PlaneDTO();
        dto.setFlightNumber(plane.getFlightNumber());
        dto.setAviaCompany(plane.getAviaCompany());
        dto.setDepartureAirport(plane.getDepartureAirport());
        dto.setArrivalAirport(plane.getArrivalAirport());
        dto.setPassengers(plane.getPassengers());
        return dto;
    }

    public static Plane toEntity(PlaneDTO dto) {
        Plane plane = new Plane();
        plane.setFlightNumber(dto.getFlightNumber());
        plane.setAviaCompany(dto.getAviaCompany());
        plane.setDepartureAirport(dto.getDepartureAirport());
        plane.setArrivalAirport(dto.getArrivalAirport());
        plane.setPassengers(dto.getPassengers());
        return plane;
    }
}
