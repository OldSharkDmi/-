package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.BaggageDTO;
import com.example.baggagetracker.model.Baggage;
import com.example.baggagetracker.model.Passenger;

public class BaggageMapper {

    public static BaggageDTO toDTO(Baggage baggage) {
        BaggageDTO dto = new BaggageDTO();
        dto.setId(baggage.getId());
        dto.setWeight(baggage.getWeight());
        dto.setInspection(baggage.getInspectionStatus());
        dto.setPassengerId(baggage.getPassenger().getBoardingPass());  // Используем ID пассажира
        return dto;
    }

    public static Baggage toEntity(BaggageDTO dto, Passenger passenger) {
        Baggage baggage = new Baggage();
        baggage.setId(dto.getId());
        baggage.setWeight(dto.getWeight());
        baggage.setInspectionStatus(dto.getInspection());
        baggage.setPassenger(passenger);
        return baggage;
    }
}

