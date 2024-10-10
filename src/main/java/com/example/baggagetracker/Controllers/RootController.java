package com.example.baggagetracker.Controllers;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public EntityModel<String> root() {
        EntityModel<String> resource = EntityModel.of("Api Системы отслежвания багажа");

        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PassengerController.class).getAllPassengers()).withRel("passengers"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PlaneController.class).getAllPlanes()).withRel("planes"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageController.class).getAllBaggages()).withRel("baggage"));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AirportController.class).getAllAirports()).withRel("airport"));
        //resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BaggageClaimController.class).getAllBaggageClaim()).withRel("baggage claim"));
        //resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TerminalController.class).getTerminal()).withRel("terminal"));

        return resource;
    }
}
