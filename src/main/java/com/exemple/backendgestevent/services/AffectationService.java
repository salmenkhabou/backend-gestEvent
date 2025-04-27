package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Affectation;
import com.exemple.backendgestevent.repository.AffectationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AffectationService {

    // Création du logger Log4j
    private static final Logger logger = LogManager.getLogger(AffectationService.class);

    @Autowired
    private AffectationRepository affectationRepository;

    // Créer une affectation
    public Affectation createAffectation(Affectation affectation) {
        logger.info("Création d'une nouvelle affectation pour l'événement ID : {}", affectation.getEventId().getId());
        Affectation createdAffectation = affectationRepository.save(affectation);
        logger.info("Affectation créée avec succès pour l'événement ID : {}", createdAffectation.getEventId().getId());
        return createdAffectation;
    }

    // Obtenir une affectation par ID
    public Optional<Affectation> getAffectationById(UUID id) {
        logger.info("Recherche de l'affectation avec ID : {}", id);
        Optional<Affectation> affectation = affectationRepository.findById(id);
        if (affectation.isPresent()) {
            logger.info("Affectation trouvée : {}");
        } else {
            logger.error("Affectation avec l'ID {} non trouvée.", id);
        }
        return affectation;
    }

    // Obtenir toutes les affectations
    public Iterable<Affectation> getAllAffectations() {
        logger.info("Récupération de toutes les affectations.");
        Iterable<Affectation> affectations = affectationRepository.findAll();
        int count = 0;
        for (Affectation affectation : affectations) {
            count++;
        }
        logger.info("Nombre d'affectations récupérées : {}", count);
        return affectations;
    }
}
