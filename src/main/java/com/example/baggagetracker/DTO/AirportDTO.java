package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Plane;
import com.example.baggagetracker.model.Terminal;

import java.util.List;

public class AirportDTO {
    private String IATA;
    private List<Long> departingPlanes;
    private List<Long> arrivingPlanes;
    private String terminalId;
    public String getIATA() {
        return IATA;
    }

    public void setIATA(String IATA) {
        this.IATA = IATA;
    }

    public List<Long> getDepartingPlanes() {
        return departingPlanes;
    }

    public void setDepartingPlanes(List<Long> departingPlanes) {
        this.departingPlanes = departingPlanes;
    }

    public List<Long> getArrivingPlanes() {
        return arrivingPlanes;
    }

    public void setArrivingPlanes(List<Long> arrivingPlanes) {
        this.arrivingPlanes = arrivingPlanes;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalId() {
        return terminalId;
    }
}
