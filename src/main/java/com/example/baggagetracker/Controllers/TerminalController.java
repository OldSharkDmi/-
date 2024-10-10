package com.example.baggagetracker.Controllers;

import com.example.baggagetracker.DTO.TerminalDTO;
import com.example.baggagetracker.Exceptions.ResourceNotFoundException;
import com.example.baggagetracker.Repository.TerminalRepository;
import com.example.baggagetracker.mappers.TerminalMapper;
import com.example.baggagetracker.model.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/terminals")
public class TerminalController {

    private final TerminalRepository terminalRepository;

    public TerminalController(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    @GetMapping
    public List<EntityModel<TerminalDTO>> getAllTerminals() {
        return terminalRepository.findAll().stream()
                .map(TerminalMapper::toDTO)
                .map(dto -> EntityModel.of(dto,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getTerminal(dto.getNumber())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getAllTerminals()).withRel("terminals")))
                .collect(Collectors.toList());
    }

    @GetMapping("/{number}")
    public EntityModel<TerminalDTO> getTerminal(@PathVariable String number) {
        Terminal terminal = terminalRepository.findById(number)
                .orElseThrow(() -> new ResourceNotFoundException("Terminal not found with number " + number));

        TerminalDTO dto = TerminalMapper.toDTO(terminal);

        return EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getTerminal(number)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getAllTerminals()).withRel("terminals"));
    }

    @PostMapping
    public ResponseEntity<EntityModel<TerminalDTO>> createTerminal(@RequestBody TerminalDTO terminalDTO) {
        Terminal terminal = TerminalMapper.toEntity(terminalDTO);
        Terminal savedTerminal = terminalRepository.save(terminal);

        EntityModel<TerminalDTO> resource = EntityModel.of(TerminalMapper.toDTO(savedTerminal),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getTerminal(savedTerminal.getNumber())).withSelfRel());

        return ResponseEntity.created(resource.getRequiredLink("self").toUri()).body(resource);
    }


    @PutMapping("/{number}")
    public ResponseEntity<EntityModel<TerminalDTO>> updateTerminal(@PathVariable String number, @RequestBody TerminalDTO terminalDTO) {
        Terminal terminal = terminalRepository.findById(number)
                .orElseThrow(() -> new ResourceNotFoundException("Terminal not found with number " + number));

        terminal.setNumber(terminalDTO.getNumber());

        Terminal updatedTerminal = terminalRepository.save(terminal);
        TerminalDTO dto = TerminalMapper.toDTO(updatedTerminal);

        return ResponseEntity.ok(EntityModel.of(dto,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getTerminal(number)).withSelfRel()));
    }


    @DeleteMapping("/{number}")
    public ResponseEntity<Void> deleteTerminal(@PathVariable String number) {
        Terminal terminal = terminalRepository.findById(number)
                .orElseThrow(() -> new ResourceNotFoundException("Terminal not found with number " + number));

        terminalRepository.delete(terminal);
        return ResponseEntity.noContent().build();
    }
}
