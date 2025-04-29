package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Affectation;
import com.exemple.backendgestevent.entity.Evenement;
import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.services.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/evenements")
@CrossOrigin("*")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;

    // Create event with personnel assignments
    @PostMapping("/create")
    public ResponseEntity<Evenement> createEvent(@RequestBody Affectation affectation) {
        Evenement evenement = affectation.getEventId();
        Personnel personnels = affectation.getPersonnel();

        // Create the event and assign personnel
        Evenement createdEvent = evenementService.createEvent(evenement, personnels);

        // Return created event in the response
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    // Update event
    @PutMapping("/update/{id}")
    public ResponseEntity<Evenement> updateEvent(@PathVariable UUID id, @RequestBody Evenement evenement) {
        Evenement updatedEvenement = evenementService.updateEvent(id, evenement);
        if (updatedEvenement != null) {
            return new ResponseEntity<>(updatedEvenement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete event
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        boolean isDeleted = evenementService.deleteEvent(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get event by ID
    @GetMapping("/{id}")
    public ResponseEntity<Evenement> getEventById(@PathVariable UUID id) {
        Evenement evenement = evenementService.getEventById(id);
        if (evenement != null) {
            return new ResponseEntity<>(evenement, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all events
    @GetMapping("/")
    public ResponseEntity<Iterable<Evenement>> getAllEvents() {
        Iterable<Evenement> events = evenementService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Get upcoming events
    @GetMapping("/upcoming")
    public ResponseEntity<?> getUpcomingEvents() {
        return ResponseEntity.ok(evenementService.getUpcomingEvents());
    }

    // Get event statistics
    @GetMapping("/statistics/{eventId}")
    public ResponseEntity<?> getEventStatistics(@PathVariable UUID eventId) {
        String report = evenementService.generateEventStatistics(eventId);
        return ResponseEntity.ok(report);
    }
}
