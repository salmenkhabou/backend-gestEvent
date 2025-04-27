package com.exemple.backendgestevent.repository;

import com.exemple.backendgestevent.entity.Personnel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends CrudRepository<Personnel, UUID> {
    //Optional<Personnel> findByEmail(String email);
    Personnel findByEmail(String email);
}
