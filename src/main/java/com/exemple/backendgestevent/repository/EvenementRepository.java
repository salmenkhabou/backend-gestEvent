package com.exemple.backendgestevent.repository;

import com.exemple.backendgestevent.entity.Evenement;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EvenementRepository extends CrudRepository<Evenement, UUID> {
    List<Evenement> findByDateAfter(Date currentDate);
}
