package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.services.AuthService;
import com.exemple.backendgestevent.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/personnels")
@CrossOrigin("*")
public class PersonnelController {

    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Personnel>> getAllPersonnels() {
        Iterable<Personnel> personnels = authService.getAllPersonnels();
        return ResponseEntity.ok(personnels);
    }
    // Récupérer tous les personnels disponibles
    @GetMapping("/available")
    public ResponseEntity<List<Personnel>> getAvailablePersonnels() {
        List<Personnel> availablePersonnels = personnelService.getAvailablePersonnels();
        if (availablePersonnels.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(availablePersonnels, HttpStatus.OK);
    }

}