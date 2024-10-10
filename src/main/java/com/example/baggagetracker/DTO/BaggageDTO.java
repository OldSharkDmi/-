package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Passenger;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class BaggageDTO {
    private Long id;
    private double weight;
    private String inspection;


    private Long passengerId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public Long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public String getInspection() {
        return inspection;
    }

}
