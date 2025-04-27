package com.exemple.backendgestevent.repository;

import com.exemple.backendgestevent.entity.Personnel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PersonnelRepository extends CrudRepository<Personnel, UUID> {
    List<Personnel> findByDisponibleTrue();
}
