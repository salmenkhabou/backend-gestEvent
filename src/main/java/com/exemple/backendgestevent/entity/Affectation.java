package com.exemple.backendgestevent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;
@RedisHash("Affectation")
public class Affectation implements Serializable {
    @Id
    private UUID id;
    Evenement eventId;
    Personnel personnelId;

    public Affectation(Evenement eventId, Personnel personnelId) {
        this.eventId = eventId;
        this.personnelId = personnelId;
    }

    public Affectation() {
    }

    public Evenement getEventId() {
        return eventId;
    }

    public void setEventId(Evenement eventId) {
        this.eventId = eventId;
    }

    public Personnel getPersonnelId() {
        return personnelId;
    }

    public void setPersonnelId(Personnel personnelId) {
        this.personnelId = personnelId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
