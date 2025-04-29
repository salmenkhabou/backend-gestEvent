package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Affectation;
import com.exemple.backendgestevent.entity.Evenement;
import com.exemple.backendgestevent.entity.Participant;
import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.repository.AffectationRepository;
import com.exemple.backendgestevent.repository.EvenementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EvenementService {

    // Création du logger Log4j
    private static final Logger logger = LogManager.getLogger(EvenementService.class);

    @Autowired
    private EvenementRepository evenementRepository;
    @Autowired
    private AffectationRepository affectationRepository;

    @Autowired
    private ParticipantService participantService;
    @Autowired
    private AffectationService affectationService;

    // Créer un événement et affecter les personnels
//    public Evenement createEvent(Evenement evenement, List<Personnel> personnels) {
//        // Sauvegarde de l'événement
//        Evenement createdEvent = evenementRepository.save(evenement);
//
//        // Création des affectations pour chaque personnel
//        for (Personnel personnel : personnels) {
//            Affectation affectation = new Affectation(createdEvent, (List<Personnel>) personnel);  // Créer une affectation
//            affectationService.createAffectation(affectation, (List<Personnel>) personnel);  // Sauvegarder l'affectation
//        }
//
//        return createdEvent;
//    }
    public Evenement createEvent(Evenement evenement, Personnel personnels) {
        // Sauvegarde de l'événement
        evenement.setId(UUID.randomUUID());
        Evenement createdEvent = evenementRepository.save(evenement);

        // Création des affectations pour chaque personnel
            Affectation affectation = new Affectation(createdEvent, personnels);  // Créer une affectation
            affectationService.createAffectation(affectation, personnels);  // Sauvegarder l'affectation


        return createdEvent;
    }

    // Update event
    public Evenement updateEvent(UUID id, Evenement evenement) {
        logger.info("Mise à jour de l'événement avec ID : {}", id);
        Optional<Evenement> existingEvent = evenementRepository.findById(id);
        if (existingEvent.isPresent()) {
            Evenement updatedEvent = existingEvent.get();
            updatedEvent.setTitle(evenement.getTitle());
            updatedEvent.setDescription(evenement.getDescription());
            updatedEvent.setDate(evenement.getDate());
            updatedEvent.setLieu(evenement.getLieu());
            updatedEvent.setCategory(evenement.getCategory());
            updatedEvent.setMax_participants(evenement.getMax_participants());
            updatedEvent.setType(evenement.getType());
            evenementRepository.save(updatedEvent);
            logger.info("Événement mis à jour avec succès : {}", updatedEvent.getTitle());
            return updatedEvent;
        } else {
            logger.error("Événement avec l'ID {} non trouvé pour la mise à jour.", id);
        }
        return null;
    }

    // Delete event
    public boolean deleteEvent(UUID id) {
        logger.info("Suppression de l'événement avec ID : {}", id);
        Optional<Evenement> event = evenementRepository.findById(id);
        if (event.isPresent()) {
            evenementRepository.delete(event.get());
            logger.info("Événement supprimé avec succès : {}", id);
            return true;
        } else {
            logger.error("Événement avec l'ID {} non trouvé pour suppression.", id);
        }
        return false;
    }

    // Get event by ID
    public Evenement getEventById(UUID id) {
        logger.info("Recherche de l'événement avec ID : {}", id);
        Evenement evenement = evenementRepository.findById(id).orElse(null);
        if (evenement != null) {
            logger.info("Événement trouvé : {}", evenement.getTitle());
        } else {
            logger.error("Événement avec l'ID {} non trouvé.", id);
        }
        return evenement;
    }

    // Get all events
    public Iterable<Evenement> getAllEvents() {
        logger.info("Récupération de tous les événements.");
        Iterable<Evenement> events = evenementRepository.findAll();
        logger.info("Nombre d'événements récupérés : {}", ((List<?>) events).size());
        return events;
    }

    // Get upcoming events
    public List<Evenement> getUpcomingEvents() {
        Date currentDate = new Date();
        logger.info("Recherche des événements à venir après : {}", currentDate);
        List<Evenement> upcomingEvents = evenementRepository.findByDateAfter(currentDate); // Filtrer les événements futurs
        logger.info("Nombre d'événements à venir trouvés : {}", upcomingEvents.size());
        return upcomingEvents;
    }

    // Generate event statistics (participants count, present participants)
    public String generateEventStatistics(UUID eventId) {
        logger.info("Génération des statistiques pour l'événement ID : {}", eventId);
        Evenement evenement = evenementRepository.findById(eventId).orElse(null);
        if (evenement == null) {
            logger.error("Événement avec l'ID {} non trouvé pour générer les statistiques.", eventId);
            return "Event not found";
        }

        List<Participant> participants = participantService.getParticipantsByEventId(eventId);
        int totalParticipants = participants.size();
        long participantsPresent = participants.stream().filter(Participant::isPresent).count();

        String report = String.format("Event: %s\nTotal Participants: %d\nParticipants Present: %d",
                evenement.getTitle(), totalParticipants, participantsPresent);
        logger.info("Rapport de statistiques généré pour l'événement ID {} : {}", eventId, report);
        return report;
    }
}
