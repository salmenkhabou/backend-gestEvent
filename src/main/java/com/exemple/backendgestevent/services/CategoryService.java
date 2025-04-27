package com.exemple.backendgestevent.services;

import com.exemple.backendgestevent.entity.Category;
import com.exemple.backendgestevent.repository.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    // Création du logger Log4j
    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    // Create category
    public Category createCategory(Category category) {
        logger.info("Création de la catégorie : {}", category.getName());
        Category createdCategory = categoryRepository.save(category);
        logger.info("Catégorie créée avec succès : {}", createdCategory.getName());
        return createdCategory;
    }

    // Update category
    public Category updateCategory(UUID id, Category category) {
        logger.info("Mise à jour de la catégorie avec ID : {}", id);
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            categoryRepository.save(updatedCategory);
            logger.info("Catégorie mise à jour avec succès : {}", updatedCategory.getName());
            return updatedCategory;
        } else {
            logger.error("Catégorie avec l'ID {} non trouvée pour mise à jour.", id);
        }
        return null;
    }

    // Delete category
    public boolean deleteCategory(UUID id) {
        logger.info("Suppression de la catégorie avec ID : {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.delete(category.get());
            logger.info("Catégorie supprimée avec succès : {}", id);
            return true;
        } else {
            logger.error("Catégorie avec l'ID {} non trouvée pour suppression.", id);
        }
        return false;
    }

    // Get category by ID
    public Category getCategoryById(UUID id) {
        logger.info("Recherche de la catégorie avec ID : {}", id);
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            logger.info("Catégorie trouvée : {}", category.getName());
        } else {
            logger.error("Catégorie avec l'ID {} non trouvée.", id);
        }
        return category;
    }

    // Get all categories
    public Iterable<Category> getAllCategories() {
        logger.info("Récupération de toutes les catégories.");
        Iterable<Category> categories = categoryRepository.findAll();
        int count = 0;
        for (Category category : categories) {
            count++;
        }
        logger.info("Nombre de catégories récupérées : {}", count);
        return categories;
    }
}
