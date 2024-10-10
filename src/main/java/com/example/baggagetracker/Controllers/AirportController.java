package com.example.baggagetracker.Controllers;

import com.example.baggagetracker.DTO.AirportDTO;
import com.example.baggagetracker.Exceptions.ResourceNotFoundException;
import com.example.baggagetracker.Repository.AirportRepository;
import com.example.baggagetracker.mappers.AirportMapper;
import com.example.baggagetracker.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    @GetMapping
    public List<EntityModel<AirportDTO>> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toDTO)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAirport(dto.getIATA())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAllAirports()).withRel("airports")))
                .collect(Collectors.toList());
    }


    @GetMapping("/{IATA}")
    public EntityModel<AirportDTO> getAirport(@PathVariable String IATA) {
        Airport airport = airportRepository.findById(IATA)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with IATA " + IATA));

        AirportDTO dto = AirportMapper.toDTO(airport);

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAirport(IATA)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAllAirports()).withRel("airports"));
    }


    @PostMapping
    public ResponseEntity<EntityModel<AirportDTO>> createAirport(@RequestBody AirportDTO airportDTO) {
        Airport airport = AirportMapper.toEntity(airportDTO);
        Airport savedAirport = airportRepository.save(airport);

        EntityModel<AirportDTO> resource = EntityModel.of(AirportMapper.toDTO(savedAirport),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAirport(savedAirport.getIATA())).withSelfRel());

        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }


    @PutMapping("/{IATA}")
    public ResponseEntity<EntityModel<AirportDTO>> updateAirport(@PathVariable String IATA, @RequestBody AirportDTO airportDTO) {
        Airport airport = airportRepository.findById(IATA)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with IATA " + IATA));

        airport.setTerminal(airportDTO.getTerminal());

        Airport updatedAirport = airportRepository.save(airport);
        AirportDTO dto = AirportMapper.toDTO(updatedAirport);

        return ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAirport(IATA)).withSelfRel()));
    }


    @DeleteMapping("/{IATA}")
    public ResponseEntity<Void> deleteAirport(@PathVariable String IATA) {
        Airport airport = airportRepository.findById(IATA)
                .orElseThrow(() -> new ResourceNotFoundException("Airport not found with IATA " + IATA));

        airportRepository.delete(airport);
        return ResponseEntity.noContent().build();
    }
}
