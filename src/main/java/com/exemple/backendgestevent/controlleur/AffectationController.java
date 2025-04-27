package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Affectation;
import com.exemple.backendgestevent.services.AffectationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/affectations")
@CrossOrigin("*")
public class AffectationController {

    @Autowired
    private AffectationService affectationService;

    // Créer une affectation
    @PostMapping("/create")
    public ResponseEntity<Affectation> createAffectation(@RequestBody Affectation affectation) {
        Affectation createdAffectation = affectationService.createAffectation(affectation);
        return new ResponseEntity<>(createdAffectation, HttpStatus.CREATED);
    }

    // Obtenir une affectation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Affectation>> getAffectationById(@PathVariable UUID id) {
        Optional<Affectation> affectation = affectationService.getAffectationById(id);
        if (affectation.isPresent()) {
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
}
