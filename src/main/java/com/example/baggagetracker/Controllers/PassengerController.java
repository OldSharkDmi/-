package com.example.baggagetracker.Controllers;

import com.example.baggagetracker.DTO.PassengerDTO;
import com.example.baggagetracker.Exceptions.PassengerNotFoundException;
import com.example.baggagetracker.Exceptions.ResourceNotFoundException;
import com.example.baggagetracker.mappers.PassengerMapper;
import com.example.baggagetracker.model.Passenger;
import com.example.baggagetracker.Repository.PassengerRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerRepository passengerRepository;

    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @GetMapping
    public List<EntityModel<PassengerDTO>> getAllPassengers() {
        return passengerRepository.findAll().stream()
                .map(PassengerMapper::toDTO)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getPassenger(dto.getBoardingPass())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getAllPassengers()).withRel("passengers")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{boardingPass}")
    public EntityModel<PassengerDTO> getPassenger(@PathVariable Long boardingPass) {
        Passenger passenger = passengerRepository.findById(boardingPass)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found with id " + boardingPass));

        PassengerDTO dto = PassengerMapper.toDTO(passenger);

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getPassenger(boardingPass)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getAllPassengers()).withRel("passengers"));
    }

    @PostMapping
    public ResponseEntity<EntityModel<PassengerDTO>> createPassenger(@RequestBody PassengerDTO passengerDTO) {
        Passenger passenger = PassengerMapper.toEntity(passengerDTO);
        Passenger savedPassenger = passengerRepository.save(passenger);
        PassengerDTO dto = PassengerMapper.toDTO(savedPassenger);

        EntityModel<PassengerDTO> resource = EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getPassenger(dto.getBoardingPass())).withSelfRel());

        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }

    @PutMapping("/{boardingPass}")
    public ResponseEntity<EntityModel<PassengerDTO>> updatePassenger(@PathVariable Long boardingPass, @RequestBody PassengerDTO passengerDTO) {
        Passenger passenger = passengerRepository.findById(boardingPass)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        passenger.setName(passengerDTO.getName());
        passenger.setSurname(passengerDTO.getSurname());
        passenger.setPassport(passengerDTO.getPassport());
        passenger.setPhone(passengerDTO.getPhone());

        Passenger updatedPassenger = passengerRepository.save(passenger);
        PassengerDTO dto = PassengerMapper.toDTO(updatedPassenger);

        return ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getPassenger(boardingPass)).withSelfRel()));
    }

    @DeleteMapping("/{boardingPass}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long boardingPass) {
        Passenger passenger = passengerRepository.findById(boardingPass)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        passengerRepository.delete(passenger);
        return ResponseEntity.noContent().build();
    }
}