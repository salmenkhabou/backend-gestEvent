package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.env.JwtUtil;
import com.exemple.backendgestevent.repository.AuthRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class AuthService {

    // Création du logger Log4j
    private static final Logger logger = LogManager.getLogger(AuthService.class);

    @Autowired
    private AuthRepository personnelRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Get all personnels
    public Iterable<Personnel> getAllPersonnels() {
        logger.info("Récupération de tous les personnels.");
        Iterable<Personnel> personnels = personnelRepository.findAll();
        logger.info("Nombre de personnels récupérés : {}", ((Iterable<?>) personnels).spliterator().estimateSize());
        return personnels;
    }

    // Register personnel
    public Personnel registerPersonnel(Personnel personnel) {
        logger.info("Enregistrement d'un nouveau personnel : {}", personnel.getEmail());
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        personnel.setPassword(encoder.encode(personnel.getPassword()));
        personnel.setId(UUID.randomUUID());
        Personnel savedPersonnel = personnelRepository.save(personnel);
        logger.info("Personnel enregistré avec succès : {}", savedPersonnel.getEmail());
        return savedPersonnel;
    }

    // Authenticate personnel
    public ResponseEntity<?> authenticatePersonnel(Personnel personnel) {
        logger.info("Tentative d'authentification pour : {}", personnel.getEmail());
        Personnel existingPersonnel = personnelRepository.findByEmail(personnel.getEmail());

        if (existingPersonnel == null) {
            logger.error("Personnel introuvable avec l'email : {}", personnel.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Personnel introuvable");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(personnel.getPassword(), existingPersonnel.getPassword())) {
            logger.error("Mot de passe incorrect pour le personnel : {}", personnel.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect");
        }

        String token = jwtUtil.generateToken(
                existingPersonnel.getName(),
                String.valueOf(existingPersonnel.getRole()),
                existingPersonnel.getEmail(),
                existingPersonnel.getId().toString()
        );
        logger.info("Authentification réussie pour : {}", existingPersonnel.getEmail());
        return ResponseEntity.ok(Collections.singletonMap("accessToken", token));
    }

}
