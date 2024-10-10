package com.example.baggagetracker.Controllers;

import com.example.baggagetracker.DTO.PlaneDTO;
import com.example.baggagetracker.Exceptions.ResourceNotFoundException;
import com.example.baggagetracker.Repository.PlaneRepository;
import com.example.baggagetracker.mappers.PlaneMapper;
import com.example.baggagetracker.model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/planes")
public class PlaneController {

    private final PlaneRepository planeRepository;

    public PlaneController(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @GetMapping
    public List<EntityModel<PlaneDTO>> getAllPlanes() {
        return planeRepository.findAll().stream()
                .map(PlaneMapper::toDTO)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getPlane(dto.getFlightNumber())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getAllPlanes()).withRel("planes")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{flightNumber}")
    public EntityModel<PlaneDTO> getPlane(@PathVariable Long flightNumber) {
        Plane plane = planeRepository.findById(flightNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Plane not found with flight number " + flightNumber));

        PlaneDTO dto = PlaneMapper.toDTO(plane);

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getPlane(flightNumber)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getAllPlanes()).withRel("planes"));
    }


    @PostMapping
    public ResponseEntity<EntityModel<PlaneDTO>> createPlane(@RequestBody PlaneDTO planeDTO) {
        Plane plane = PlaneMapper.toEntity(planeDTO);
        Plane savedPlane = planeRepository.save(plane);

        EntityModel<PlaneDTO> resource = EntityModel.of(PlaneMapper.toDTO(savedPlane),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getPlane(savedPlane.getFlightNumber())).withSelfRel());

        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }


    @PutMapping("/{flightNumber}")
    public ResponseEntity<EntityModel<PlaneDTO>> updatePlane(@PathVariable Long flightNumber, @RequestBody PlaneDTO planeDTO) {
        Plane plane = planeRepository.findById(flightNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Plane not found with flight number " + flightNumber));

        plane.setAviaCompany(planeDTO.getAviaCompany());  // Обновляем только название авиакомпании, например

        Plane updatedPlane = planeRepository.save(plane);
        PlaneDTO dto = PlaneMapper.toDTO(updatedPlane);

        return ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getPlane(flightNumber)).withSelfRel()));
    }


    @DeleteMapping("/{flightNumber}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long flightNumber) {
        Plane plane = planeRepository.findById(flightNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Plane not found with flight number " + flightNumber));

        planeRepository.delete(plane);
        return ResponseEntity.noContent().build();
    }
}