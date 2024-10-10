package com.example.baggagetracker.Repository;

import com.example.baggagetracker.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
