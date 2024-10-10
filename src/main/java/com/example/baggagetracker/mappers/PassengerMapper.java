package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.PassengerDTO;
import com.example.baggagetracker.model.Passenger;

public class PassengerMapper {

    public static PassengerDTO toDTO(Passenger passenger) {
        PassengerDTO dto = new PassengerDTO();
        dto.setBoardingPass(passenger.getBoardingPass());
        dto.setName(passenger.getName());
        dto.setSurname(passenger.getSurname());
        dto.setPassport(passenger.getPassport());
        dto.setPhone(passenger.getPhone());
        return dto;
    }

    public static Passenger toEntity(PassengerDTO dto) {
        Passenger passenger = new Passenger();
        passenger.setBoardingPass(dto.getBoardingPass());
        passenger.setName(dto.getName());
        passenger.setSurname(dto.getSurname());
        passenger.setPassport(dto.getPassport());
        passenger.setPhone(dto.getPhone());
        return passenger;
    }
}
