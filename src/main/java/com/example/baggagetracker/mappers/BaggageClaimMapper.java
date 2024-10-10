package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.BaggageClaimDTO;
import com.example.baggagetracker.model.BaggageClaim;

public class BaggageClaimMapper {

    public static BaggageClaimDTO toDTO(BaggageClaim baggageClaim) {
        BaggageClaimDTO dto = new BaggageClaimDTO();
        dto.setId(baggageClaim.getId());
        dto.setNumberTape(baggageClaim.getNumberTape());
        dto.setTerminal(baggageClaim.getTerminal());
        return dto;
    }

    public static BaggageClaim toEntity(BaggageClaimDTO dto) {
        BaggageClaim baggageClaim = new BaggageClaim();
        baggageClaim.setId(dto.getId());
        baggageClaim.setNumberTape(dto.getNumberTape());
        baggageClaim.setTerminal(dto.getTerminal());
        return baggageClaim;
    }
}
