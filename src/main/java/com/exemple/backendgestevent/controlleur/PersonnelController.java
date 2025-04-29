package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/personnels")
@CrossOrigin("*")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;

    // Créer un personnel
    @PostMapping("/create")
    public ResponseEntity<Personnel> createPersonnel(@RequestBody Personnel personnel) {
        Personnel createdPersonnel = personnelService.createPersonnel(personnel);
        return new ResponseEntity<>(createdPersonnel, HttpStatus.CREATED);
    }

    // Obtenir un personnel par ID
    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getPersonnelById(@PathVariable UUID id) {
        Personnel personnel = personnelService.getPersonnelById(id).orElse(null);
        if (personnel != null) {
            return new ResponseEntity<>(personnel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtenir tous les personnels
    @GetMapping("/")
    public ResponseEntity<Iterable<Personnel>> getAllPersonnels() {
        Iterable<Personnel> personnels = personnelService.getAllPersonnels();
        return new ResponseEntity<>(personnels, HttpStatus.OK);
    }

    // Obtenir tous les personnels disponibles
    @GetMapping("/available")
    public ResponseEntity<List<Personnel>> getAvailablePersonnels() {
        List<Personnel> availablePersonnels = personnelService.getAvailablePersonnels();
        return new ResponseEntity<>(availablePersonnels, HttpStatus.OK);
    }

    // Mettre à jour un personnel
    @PutMapping("/update/{id}")
    public ResponseEntity<Personnel> updatePersonnel(@PathVariable UUID id, @RequestBody Personnel personnel) {
        Personnel updatedPersonnel = personnelService.updatePersonnel(id, personnel);
        if (updatedPersonnel != null) {
            return new ResponseEntity<>(updatedPersonnel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un personnel
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable UUID id) {
        boolean isDeleted = personnelService.deletePersonnel(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
