package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Participant;
import com.exemple.backendgestevent.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/participants")
@CrossOrigin("*")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    // Register a participant
    @PostMapping("/register")
    public ResponseEntity<Participant> registerParticipant(@RequestBody Participant participant) {
        Participant createdParticipant = participantService.registerParticipant(participant);
        return new ResponseEntity<>(createdParticipant, HttpStatus.CREATED);
    }

    // Get participant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable UUID id) {
        Participant participant = participantService.getParticipantById(id);
        if (participant != null) {
            return new ResponseEntity<>(participant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all participants
    @GetMapping("/")
    public ResponseEntity<Iterable<Participant>> getAllParticipants() {
        Iterable<Participant> participants = participantService.getAllParticipants();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
    @GetMapping("/byEvent/{eventId}")
    public ResponseEntity<List<Participant>> getParticipantsByEventId(@PathVariable UUID eventId) {
        List<Participant> participants = participantService.getParticipantsByEventId(eventId);
        if (participants.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
    @PutMapping("/{id}/present")
    public ResponseEntity<Participant> markAsPresent(@PathVariable UUID id) {
        Participant updatedParticipant = participantService.markAsPresent(id);
        if (updatedParticipant != null) {
            return new ResponseEntity<>(updatedParticipant, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
