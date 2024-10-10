package com.example.baggagetracker.DTO;

import com.example.baggagetracker.model.Plane;

public class PassengerDTO {
    private Long boardingPass;
    private String name;
    private String surname;
    private String passport;
    private String phone;
    private String baggages;
    private Plane plane;

    public Long getBoardingPass() {
        return boardingPass;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassport() {
        return passport;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getBaggages() {
        return baggages;
    }

    public Long getPlane() {
        return plane.getFlightNumber();
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setBaggages(String baggages) {
        this.baggages = baggages;
    }

    public void setBoardingPass(Long boardingPass) {
        this.boardingPass = boardingPass;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
