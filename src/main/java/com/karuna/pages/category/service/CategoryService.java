package com.karuna.pages.category.service;

import com.karuna.pages.category.model.Category;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface CategoryService {

    Collection<Category> getAllCategories();

    Category getCategory(Long id);

    Category saveCategory(Category category);

    Category editCategory(Long id,Category category);

    Category disbaleCategory(Long id);
}
