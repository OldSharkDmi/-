package com.example.baggagetracker.Service;

import com.example.baggagetracker.DTO.PassengerDTO;
import com.example.baggagetracker.Repository.PassengerRepository;
import com.example.baggagetracker.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<PassengerDTO> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PassengerDTO getPassengerById(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        return convertToDTO(passenger);
    }

    public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
        Passenger passenger = convertToEntity(passengerDTO);
        passengerRepository.save(passenger);
        return convertToDTO(passenger);
    }

    public PassengerDTO updatePassenger(Long id, PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        passenger.setName(passengerDTO.getName());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setPassport(passengerDTO.getPassport());
        passenger.setPhone(passengerDTO.getPhone());

        passengerRepository.save(passenger);
        return convertToDTO(passenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }

    private PassengerDTO convertToDTO(Passenger passenger) {
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO.setBoardingPass(passenger.getBoardingPass());
        passengerDTO.setName(passenger.getName());
        passengerDTO.setSurname(passenger.getSurname());
        passengerDTO.setPassport(passenger.getPassport());
        passengerDTO.setPhone(passenger.getPhone());
        passengerDTO.setPlane(passenger.getPlane());
        return passengerDTO;
    }

    private Passenger convertToEntity(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        passenger.setName(passengerDTO.getName());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setPassport(passengerDTO.getPassport());
        passenger.setPhone(passengerDTO.getPhone());

        return passenger;
    }
}
