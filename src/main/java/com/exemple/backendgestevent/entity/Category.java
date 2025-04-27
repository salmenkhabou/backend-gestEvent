package com.exemple.backendgestevent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("Category")
public class Category implements Serializable {
    @Id
    private UUID id;
    private String name;
    public Category() {}
    public Category(String name) {
        this.name = name;
    }
    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
