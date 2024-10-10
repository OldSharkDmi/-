package com.example.baggagetracker.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;


@Entity
public class Baggage {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double weight;
    private String inspection;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    @JsonManagedReference
    private Passenger passenger;

    public void setId(Long id) {
        this.id = id;
    }

    public void setInspectionStatus(String inspection) {
        this.inspection = inspection;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }


    public String getInspectionStatus() {
        return inspection;
    }
    public Passenger getPassenger() {
        return passenger;
    }

    public double getWeight() {
        return weight;
    }




    public Long getId() {
        return id;
    }
}
