package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Affectation;
import com.exemple.backendgestevent.services.AffectationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/affectations")
@CrossOrigin("*")
public class AffectationController {

    @Autowired
    private AffectationService affectationService;

    // Créer une affectation avec l'événement et la liste de personnels
    @PostMapping("/create")
    public ResponseEntity<Affectation> createAffectation(@RequestBody Affectation affectation) {
        // Vérifier si l'événement et la liste des personnels sont bien fournis
        if (affectation.getEventId() == null || affectation.getPersonnel() == null ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Appeler le service pour créer l'affectation
        Affectation createdAffectation = affectationService.createAffectation(affectation, affectation.getPersonnel());

        return new ResponseEntity<>(createdAffectation, HttpStatus.CREATED);
    }

    // Obtenir une affectation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Affectation> getAffectationById(@PathVariable UUID id) {
        Affectation affectation = affectationService.getAffectationById(id).orElse(null);

        if (affectation != null) {
            return new ResponseEntity<>(affectation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtenir toutes les affectations
    @GetMapping("/")
    public ResponseEntity<Iterable<Affectation>> getAllAffectations() {
        Iterable<Affectation> affectations = affectationService.getAllAffectations();
        return new ResponseEntity<>(affectations, HttpStatus.OK);
    }
    @GetMapping("/event/{eventId}")
    public ResponseEntity<Affectation> getAffectationByEventId(@PathVariable UUID eventId) {
        Affectation affectations = affectationService.getAffectationByEventId(eventId);
        if (affectations== null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if no affectations found
        }
        return new ResponseEntity<>(affectations, HttpStatus.OK);  // Return 200 with list of affectations
    }

}
