package com.karuna.pages.category.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.repository.CategoryRepository;
import com.karuna.pages.core.exceptions.BadRequestException;
import com.karuna.pages.core.exceptions.ResourceNotFoundException;
import com.karuna.pages.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Category> getAllCategories() {
        return categoryRepository.findAllByActive(1);
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
    public Category editCategory(Long id, Category category) {
        if(id == null) throw new BadRequestException("Category id cannot be null");

        if(category == null) return null;

        Category savedCategory = categoryRepository.getCategoryById(id);

        if(savedCategory == null) throw new ResourceNotFoundException("Category with id " + id + " not found");

        return categoryRepository.save(category);
    }

    @Override
    public Category disbaleCategory(Long id) {
        Category category =  categoryRepository.getCategoryById(id);
        category.setActive(0);
        return categoryRepository.save(category);
    }
}
