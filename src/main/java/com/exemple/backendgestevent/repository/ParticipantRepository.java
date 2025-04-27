package com.exemple.backendgestevent.repository;

import com.exemple.backendgestevent.entity.Participant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ParticipantRepository extends CrudRepository<Participant, UUID> {
    List<Participant> findByIdEventId(UUID eventId);

}
