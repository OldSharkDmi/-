package com.example.baggagetracker.model;

import jakarta.persistence.*;

@Entity
public class BaggageClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numberTape;

    @ManyToOne
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    public Terminal getTerminal() {
        return terminal;
    }

    public Long getId() {
        return id;
    }

    public String getNumberTape() {
        return numberTape;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumberTape(String numberTape) {
        this.numberTape = numberTape;
    }
}
