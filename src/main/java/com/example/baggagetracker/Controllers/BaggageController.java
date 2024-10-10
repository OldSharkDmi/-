package com.example.baggagetracker.Controllers;
import com.example.baggagetracker.DTO.BaggageDTO;
import com.example.baggagetracker.Exceptions.BaggageNotFoundException;
import com.example.baggagetracker.Exceptions.ResourceNotFoundException;
import com.example.baggagetracker.Repository.PassengerRepository;
import com.example.baggagetracker.mappers.BaggageMapper;
import com.example.baggagetracker.model.Baggage;
import com.example.baggagetracker.model.Passenger;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.baggagetracker.Repository.BaggageRepository;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/baggages")
public class BaggageController {

    private final BaggageRepository baggageRepository;
    private final PassengerRepository passengerRepository;

    public BaggageController(BaggageRepository baggageRepository, PassengerRepository passengerRepository) {
        this.baggageRepository = baggageRepository;
        this.passengerRepository = passengerRepository;
    }

    @GetMapping
    public List<EntityModel<BaggageDTO>> getAllBaggages() {
        return baggageRepository.findAll().stream()
                .map(BaggageMapper::toDTO)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageController.class).getBaggage(dto.getId())).withSelfRel()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<BaggageDTO> getBaggage(@PathVariable Long id) {
        Baggage baggage = baggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage not found"));

        BaggageDTO dto = BaggageMapper.toDTO(baggage);

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageController.class).getBaggage(id)).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<EntityModel<BaggageDTO>> createBaggage(@RequestBody BaggageDTO baggageDTO) {
        Passenger passenger = passengerRepository.findById(baggageDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        Baggage baggage = BaggageMapper.toEntity(baggageDTO, passenger);
        Baggage savedBaggage = baggageRepository.save(baggage);

        BaggageDTO dto = BaggageMapper.toDTO(savedBaggage);

        return ResponseEntity.created(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageController.class).getBaggage(dto.getId())).toUri())
                .body(EntityModel.of(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BaggageDTO>> updateBaggage(@PathVariable Long id, @RequestBody BaggageDTO baggageDTO) {
        Baggage baggage = baggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage not found"));

        Passenger passenger = passengerRepository.findById(baggageDTO.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        baggage.setWeight(baggageDTO.getWeight());
        baggage.setInspectionStatus(baggageDTO.getInspection());
        baggage.setPassenger(passenger);

        Baggage updatedBaggage = baggageRepository.save(baggage);
        BaggageDTO dto = BaggageMapper.toDTO(updatedBaggage);

        return ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageController.class).getBaggage(id)).withSelfRel()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBaggage(@PathVariable Long id) {
        Baggage baggage = baggageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Baggage not found"));

        baggageRepository.delete(baggage);
        return ResponseEntity.noContent().build();
    }
}