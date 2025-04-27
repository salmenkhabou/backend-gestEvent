package com.exemple.backendgestevent.repository;

import com.exemple.backendgestevent.entity.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CategoryRepository extends CrudRepository<Category, UUID> {
}
