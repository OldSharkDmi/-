package com.example.baggagetracker.Controllers;

import com.example.baggagetracker.DTO.BaggageClaimDTO;
import com.example.baggagetracker.Exceptions.ResourceNotFoundException;
import com.example.baggagetracker.Repository.BaggageClaimRepository;
import com.example.baggagetracker.mappers.BaggageClaimMapper;
import com.example.baggagetracker.model.BaggageClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;



@RestController
@RequestMapping("/baggageclaims")
public class BaggageClaimController {

    private final BaggageClaimRepository baggageClaimRepository;

    public BaggageClaimController(BaggageClaimRepository baggageClaimRepository) {
        this.baggageClaimRepository = baggageClaimRepository;
    }


    @GetMapping
    public List<EntityModel<BaggageClaimDTO>> getAllBaggageClaims() {
        return baggageClaimRepository.findAll().stream()
                .map(BaggageClaimMapper::toDTO)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getBaggageClaim(dto.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getAllBaggageClaims()).withRel("baggageClaims")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<BaggageClaimDTO> getBaggageClaim(@PathVariable Long id) {
        BaggageClaim baggageClaim = baggageClaimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage claim not found with id " + id));

        BaggageClaimDTO dto = BaggageClaimMapper.toDTO(baggageClaim);

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getBaggageClaim(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getAllBaggageClaims()).withRel("baggageClaims"));
    }


    @PostMapping
    public ResponseEntity<EntityModel<BaggageClaimDTO>> createBaggageClaim(@RequestBody BaggageClaimDTO baggageClaimDTO) {
        BaggageClaim baggageClaim = BaggageClaimMapper.toEntity(baggageClaimDTO);
        BaggageClaim savedBaggageClaim = baggageClaimRepository.save(baggageClaim);

        EntityModel<BaggageClaimDTO> resource = EntityModel.of(BaggageClaimMapper.toDTO(savedBaggageClaim),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getBaggageClaim(savedBaggageClaim.getId())).withSelfRel());

        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BaggageClaimDTO>> updateBaggageClaim(@PathVariable Long id, @RequestBody BaggageClaimDTO baggageClaimDTO) {
        BaggageClaim baggageClaim = baggageClaimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage claim not found with id " + id));

        baggageClaim.setNumberTape(baggageClaimDTO.getNumberTape());

        BaggageClaim updatedBaggageClaim = baggageClaimRepository.save(baggageClaim);
        BaggageClaimDTO dto = BaggageClaimMapper.toDTO(updatedBaggageClaim);

        return ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getBaggageClaim(id)).withSelfRel()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaggageClaim(@PathVariable Long id) {
        BaggageClaim baggageClaim = baggageClaimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage claim not found with id " + id));

        baggageClaimRepository.delete(baggageClaim);
        return ResponseEntity.noContent().build();
    }
}