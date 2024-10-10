package com.example.baggagetracker.Repository;

import com.example.baggagetracker.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
