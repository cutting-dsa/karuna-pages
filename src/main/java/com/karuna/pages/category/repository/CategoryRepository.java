package com.karuna.pages.category.repository;

import com.karuna.pages.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryById(Long id);
    Collection<Category> findAllByActive(int active);
}
