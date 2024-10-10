package com.example.baggagetracker.mappers;

import com.example.baggagetracker.DTO.TerminalDTO;
import com.example.baggagetracker.model.Terminal;

public class TerminalMapper {

    public static TerminalDTO toDTO(Terminal terminal) {
        TerminalDTO dto = new TerminalDTO();
        dto.setNumber(terminal.getNumber());
        dto.setAirports(terminal.getAirports());
        return dto;
    }

    public static Terminal toEntity(TerminalDTO dto) {
        Terminal terminal = new Terminal();
        terminal.setNumber(dto.getNumber());
        terminal.setAirports(dto.getAirports());
        return terminal;
    }
}
