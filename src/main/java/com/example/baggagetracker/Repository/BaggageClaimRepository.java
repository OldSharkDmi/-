package com.example.baggagetracker.Repository;

import com.example.baggagetracker.model.BaggageClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageClaimRepository extends JpaRepository<BaggageClaim, Long> {
}
