package com.karuna.pages.category.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category editCategory(Category category) {

        return categoryRepository.save(category);
    }

    @Override
    public Category disbaleCategory(Long id) {
        Category category =  categoryRepository.getCategoryById(id);
        category.setActive(0);
        return categoryRepository.save(category);
    }
}
