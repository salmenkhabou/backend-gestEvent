package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Participant;
import com.exemple.backendgestevent.repository.ParticipantRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantService {

    // Création du logger pour cette classe
    private static final Logger logger = LogManager.getLogger(ParticipantService.class);

    @Autowired
    private ParticipantRepository participantRepository;

    // Register a new participant
    public Participant registerParticipant(Participant participant) {
        logger.info("Enregistrement du participant: {}", participant.getNom());
        Participant savedParticipant = participantRepository.save(participant);
        logger.info("Participant enregistré avec succès: {}", savedParticipant.getNom());
        return savedParticipant;
    }

    // Get participant by ID
    public Participant getParticipantById(UUID id) {
        logger.info("Recherche du participant avec l'ID: {}", id);
        Optional<Participant> participantOpt = participantRepository.findById(id);
        if (participantOpt.isPresent()) {
            logger.info("Participant trouvé: {}", participantOpt.get().getNom());
            return participantOpt.get();
        } else {
            logger.error("Aucun participant trouvé avec l'ID: {}", id);
            return null;  // Participant non trouvé
        }
    }

    // Get all participants
    public Iterable<Participant> getAllParticipants() {
        logger.info("Récupération de tous les participants.");
        Iterable<Participant> participants = participantRepository.findAll();
        logger.info("Nombre de participants récupérés: {}", ((List<?>) participants).size());
        return participants;
    }

    // Get participants by Event ID
    public List<Participant> getParticipantsByEventId(UUID eventId) {
        logger.info("Récupération des participants pour l'événement ID: {}", eventId);
        List<Participant> participants = participantRepository.findByIdEventId(eventId);
        logger.info("Nombre de participants pour l'événement ID {}: {}", eventId, participants.size());
        return participants;
    }

    // Mark participant as present
    public Participant markAsPresent(UUID participantId) {
        logger.info("Marquer le participant ID {} comme présent.", participantId);
        Optional<Participant> participantOpt = participantRepository.findById(participantId);
        if (participantOpt.isPresent()) {
            Participant participant = participantOpt.get();
            participant.setPresent(true);  // Mettre à jour le champ 'present'
            Participant updatedParticipant = participantRepository.save(participant);  // Sauvegarder l'état mis à jour
            logger.info("Participant ID {} marqué comme présent.", participantId);
            return updatedParticipant;
        } else {
            logger.error("Participant ID {} non trouvé pour le marquage.", participantId);
            return null;  // Participant non trouvé
        }
    }
}
