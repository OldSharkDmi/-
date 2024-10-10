package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Terminal;
import jakarta.persistence.*;

public class BaggageClaimDTO {


    private Long id;
    private String numberTape;

    private Terminal terminal;

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberTape(String numberTape) {
        this.numberTape = numberTape;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public Long getId() {
        return id;
    }

    public String getNumberTape() {
        return numberTape;
    }
}
