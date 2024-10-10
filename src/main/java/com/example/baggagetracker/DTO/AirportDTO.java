package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Plane;
import com.example.baggagetracker.model.Terminal;

import java.util.List;

public class AirportDTO {

    private String IATA;
    private List<Plane> departingPlanes;
    private List<Plane> arrivingPlanes;

    private Terminal terminal;

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public String getIATA() {
        return IATA;
    }



    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public void setDepartingPlanes(List<Plane> departingPlanes) {
        this.departingPlanes = departingPlanes;
    }

    public void setArrivingPlanes(List<Plane> arrivingPlanes) {
        this.arrivingPlanes = arrivingPlanes;
    }

    public List<Plane> getArrivingPlanes() {
        return arrivingPlanes;
    }

    public List<Plane> getDepartingPlanes() {
        return departingPlanes;
    }
}
