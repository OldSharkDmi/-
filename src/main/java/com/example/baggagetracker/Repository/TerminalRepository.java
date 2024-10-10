package com.example.baggagetracker.Repository;
import com.example.baggagetracker.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal, String> { }
