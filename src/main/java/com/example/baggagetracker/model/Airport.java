package com.example.baggagetracker.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Airport {

    @Id
    private String IATA;

    @OneToMany(mappedBy = "departureAirport")
    @JsonManagedReference
    private List<Plane> departingPlanes;

    @OneToMany(mappedBy = "arrivalAirport")
    @JsonManagedReference
    private List<Plane> arrivingPlanes;

    @ManyToOne
    @JoinColumn(name = "terminal_id")
    @JsonBackReference
    private Terminal terminal;

    public void setArrivingPlanes(List<Plane> arrivingPlanes) {
        this.arrivingPlanes = arrivingPlanes;
    }

    public void setDepartingPlanes(List<Plane> departingPlanes) {
        this.departingPlanes = departingPlanes;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public List<Plane> getArrivingPlanes() {
        return arrivingPlanes;
    }

    public List<Plane> getDepartingPlanes() {
        return departingPlanes;
    }

    public String getIATA() {
        return IATA;
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
