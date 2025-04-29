package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.repository.PersonnelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonnelService {

    // Initialisation du logger Log4j
    private static final Logger logger = LogManager.getLogger(PersonnelService.class);

    @Autowired
    private PersonnelRepository personnelRepository;

    /**
     * Méthode pour obtenir tous les personnels disponibles (disponible = true).
     */
    public List<Personnel> getAvailablePersonnels() {
        logger.info("Recherche des personnels disponibles.");

        // Initialiser la liste des personnels disponibles
        List<Personnel> availablePersonnels = new ArrayList<>();

        // Récupérer tous les personnels
        List<Personnel> allPersonnels = (List<Personnel>) personnelRepository.findAll();

        // Parcourir la liste des personnels et ajouter ceux qui sont disponibles
        for (Personnel personnel : allPersonnels) {
            if (personnel.isDisponible()) {
                availablePersonnels.add(personnel);
            }
        }

        // Si aucun personnel n'est disponible
        if (availablePersonnels.isEmpty()) {
            logger.warn("Aucun personnel disponible trouvé.");
        } else {
            logger.info("Nombre de personnels disponibles trouvés : " + availablePersonnels.size());
        }

        return availablePersonnels;
    }


    /**
     * Méthode pour obtenir un personnel par son identifiant.
     */
    public Optional<Personnel> getPersonnelById(UUID id) {
        logger.info("Recherche du personnel avec l'ID : {}", id);
        Optional<Personnel> personnel = personnelRepository.findById(id);

        if (personnel.isPresent()) {
            logger.info("Personnel trouvé avec l'ID : {}", id);
        } else {
            logger.error("Aucun personnel trouvé avec l'ID : {}", id);
        }

        return personnel;
    }

    /**
     * Créer un personnel
     */
    public Personnel createPersonnel(Personnel personnel) {
        logger.info("Création du personnel : {}", personnel.getName());
        return personnelRepository.save(personnel);
    }

    /**
     * Mise à jour d'un personnel
     */
    public Personnel updatePersonnel(UUID id, Personnel personnel) {
        Optional<Personnel> existingPersonnel = personnelRepository.findById(id);
        if (existingPersonnel.isPresent()) {
            Personnel updatedPersonnel = existingPersonnel.get();
            updatedPersonnel.setName(personnel.getName());
            updatedPersonnel.setEmail(personnel.getEmail());
            updatedPersonnel.setPhone(personnel.getPhone());
            updatedPersonnel.setRole(personnel.getRole());
            updatedPersonnel.setDisponible(personnel.isDisponible());
            updatedPersonnel.setPassword(personnel.getPassword());
            return personnelRepository.save(updatedPersonnel);
        }
        return null;
    }

    /**
     * Supprimer un personnel
     */
    public boolean deletePersonnel(UUID id) {
        Optional<Personnel> personnel = personnelRepository.findById(id);
        if (personnel.isPresent()) {
            personnelRepository.delete(personnel.get());
            return true;
        }
        return false;
    }

    /**
     * Récupérer tous les personnels
     */
    public Iterable<Personnel> getAllPersonnels() {
        return personnelRepository.findAll();
    }
}
