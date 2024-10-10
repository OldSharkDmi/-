package com.example.baggagetracker.Repository;


import com.example.baggagetracker.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {
}
