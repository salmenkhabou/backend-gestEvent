package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.repository.PersonnelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * Méthode pour obtenir tous les personnels disponibles.
     */
    public List<Personnel> getAvailablePersonnels() {
        logger.info("Recherche des personnels disponibles.");
        List<Personnel> availablePersonnels = personnelRepository.findByDisponibleTrue();
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
}
