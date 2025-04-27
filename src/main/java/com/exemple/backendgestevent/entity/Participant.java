package com.exemple.backendgestevent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;
@RedisHash("Participant")
public class Participant implements Serializable {
    @Id
    private UUID id;
    private String nom;
    private String email;
    private String adresse;
    private String preferences;
    private Evenement idEvent;
    private boolean present;

    public Participant(String nom, String email, String adresse, String preferences, Evenement idEvent) {
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.preferences = preferences;
        this.idEvent = idEvent;
        this.present = false;
    }

    public Participant() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public Evenement getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Evenement idEvent) {
        this.idEvent = idEvent;
    }

    public boolean isPresent() {
        return present;  // Retourne true si le participant est présent
    }

    public void setPresent(boolean present) {
        this.present = present;  // Définit la présence du participant

    }
}
