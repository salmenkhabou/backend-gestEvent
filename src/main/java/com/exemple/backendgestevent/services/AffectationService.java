package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Affectation;
import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.repository.AffectationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AffectationService {

    // Création du logger Log4j
    private static final Logger logger = LogManager.getLogger(AffectationService.class);

    @Autowired
    private AffectationRepository affectationRepository;

    /**
     * Créer une nouvelle affectation pour un événement et associer des personnels à cet événement.
     *
     * @param affectation L'affectation à créer
     * @param personnels  La liste des personnels à assigner à cet événement
     * @return L'affectation créée
     */
//    public Affectation createAffectation(Affectation affectation, List<Personnel> personnels) {
//        // Ajout des personnels à l'affectation
//        affectation.setPersonnel(personnels);
//
//        // Enregistrement de l'affectation dans la base de données
//        logger.info("Création d'une nouvelle affectation pour l'événement ID : {}", affectation.getEventId().getId());
//        Affectation createdAffectation = affectationRepository.save(affectation);
//
//        // Log des personnels associés à l'événement
//        for (Personnel personnel : personnels) {
//            logger.info("Personnel {} assigné à l'événement ID : {}", personnel.getName(), affectation.getEventId().getId());
//        }
//
//        logger.info("Affectation créée avec succès pour l'événement ID : {}", createdAffectation.getEventId().getId());
//        return createdAffectation;
//    }
    public Affectation createAffectation(Affectation affectation, Personnel personnels) {
        // Ajout des personnels à l'affectation
        affectation.setPersonnel(personnels);

        // Enregistrement de l'affectation dans la base de données
        logger.info("Création d'une nouvelle affectation pour l'événement ID : {}", affectation.getEventId().getId());
        Affectation createdAffectation = affectationRepository.save(affectation);

        // Log des personnels associés à l'événement
        logger.info("Personnel {} assigné à l'événement ID : {}", personnels.getName(), affectation.getEventId().getId());


        logger.info("Affectation créée avec succès pour l'événement ID : {}", createdAffectation.getEventId().getId());
        return createdAffectation;
    }


    /**
     * Recherche d'une affectation par son ID.
     *
     * @param id L'ID de l'affectation
     * @return L'affectation correspondante ou null si non trouvée
     */
    public Optional<Affectation> getAffectationById(UUID id) {
        logger.info("Recherche de l'affectation avec ID : {}", id);
        Optional<Affectation> affectation = affectationRepository.findById(id);

        if (affectation.isPresent()) {
            logger.info("Affectation trouvée pour l'événement ID : {}", affectation.get().getEventId().getId());
        } else {
            logger.error("Affectation avec l'ID {} non trouvée.", id);
        }

        return affectation;
    }

    /**
     * Récupère toutes les affectations.
     *
     * @return Toutes les affectations de la base de données
     */
    public Iterable<Affectation> getAllAffectations() {
        logger.info("Récupération de toutes les affectations.");
        Iterable<Affectation> affectations = affectationRepository.findAll();

        // Comptage et log du nombre d'affectations récupérées
        long count = ((List<?>) affectations).size();
        logger.info("Nombre d'affectations récupérées : {}", count);

        return affectations;
    }

    public Affectation getAffectationByEventId(UUID eventId) {
        logger.info("Recherche des affectations pour l'événement avec l'ID : {}", eventId);
        Affectation affectation = this.findByEventId(eventId);

        if (affectation==null) {
            logger.warn("Aucune affectation trouvée pour l'événement avec l'ID : {}", eventId);
        } else {
            logger.info("Nombre d'affectations trouvées pour l'événement ID : {}", eventId);
        }

        return affectation;
    }
    public Affectation findByEventId(UUID eventId) {
        Iterable<Affectation> affectations = affectationRepository.findAll();
        Affectation affectation = null;
        for (Affectation a : affectations) {
            if (a.getEventId().getId().equals(eventId)) {
                affectation = a;
                break;
            }
        }
        return affectation;
    }
}
