package com.exemple.backendgestevent.repository;

import com.exemple.backendgestevent.entity.Affectation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface AffectationRepository extends CrudRepository<Affectation, UUID> {
    List<Affectation> findByEventId(UUID eventId);

}
