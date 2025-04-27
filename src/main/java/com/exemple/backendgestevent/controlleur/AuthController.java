package com.exemple.backendgestevent.controlleur;

import com.exemple.backendgestevent.entity.Personnel;
import com.exemple.backendgestevent.env.JwtUtil;
import com.exemple.backendgestevent.services.AuthService;
import com.exemple.backendgestevent.services.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/")
    public ResponseEntity<Iterable<Personnel>> getAllPersonnels() {
        Iterable<Personnel> personnels = authService.getAllPersonnels();
        return ResponseEntity.ok(personnels);
    }

    @PostMapping("/registration")
    public Personnel registration(@RequestBody Personnel personnel) {
        return authService.registerPersonnel(personnel);
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> connexion(@RequestBody Personnel personnel) {
        return authService.authenticatePersonnel(personnel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Personnel>> getUser(@PathVariable UUID id) {
        Optional<Personnel> personnel = personnelService.getPersonnelById(id);
        if (personnel != null) {
            return ResponseEntity.ok(personnel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
