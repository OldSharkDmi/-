package com.example.baggagetracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.persistence.Id;

import java.util.List;
@Entity
public class Passenger {

    @Id
    private Long boardingPass;

    private String name;
    private String surname;
    private String passport;
    private String phone;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Baggage> baggages;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    @JsonManagedReference
    private Plane plane;

    // Getters и Setters
    public Long getBoardingPass() {
        return boardingPass;
    }

    public void setBoardingPass(Long boardingPass) {
        this.boardingPass = boardingPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Baggage> getBaggages() {
        return baggages;
    }

    public void setBaggages(List<Baggage> baggages) {
        this.baggages = baggages;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}