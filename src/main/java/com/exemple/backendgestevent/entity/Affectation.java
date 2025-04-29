package com.exemple.backendgestevent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RedisHash("Affectation")
public class Affectation implements Serializable {
    @Id
    private UUID id;
    Evenement eventId;
    //List<Personnel> personnel ;
    Personnel personnel ;

    public Affectation(Evenement eventId, Personnel personnel) {
        this.eventId = eventId;
        this.personnel = personnel;
        //this.personnel = new ArrayList<Personnel>();
    }

    public Affectation() {
    }

    public Evenement getEventId() {
        return eventId;
    }

    public void setEventId(Evenement eventId) {
        this.eventId = eventId;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    // Getter and setter for personnel
    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
