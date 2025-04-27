package com.exemple.backendgestevent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@RedisHash("Evenement")
public class Evenement implements Serializable {
    @Id
    private UUID id;
    private String title;
    private String description;
    private Date date;
    private String lieu;
    private Category category;
    private String Max_participants;
    private String type;

    public Evenement(String description, String title, Date date, String lieu, Category category, String max_participants, String type) {
        this.description = description;
        this.title = title;
        this.date = date;
        this.lieu = lieu;
        this.category = category;
        Max_participants = max_participants;
        this.type = type;
    }

    public Evenement() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getMax_participants() {
        return Max_participants;
    }

    public void setMax_participants(String max_participants) {
        Max_participants = max_participants;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
