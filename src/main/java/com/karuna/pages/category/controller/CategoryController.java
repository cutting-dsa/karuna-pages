package com.karuna.pages.category.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/", produces = "application/json")
    public Collection<Category> getAll(){
        return categoryService.getAllCategories();
    }
    @GetMapping(path = "/{id}", produces = "application/json")
    public Category getOne(@PathVariable  Long id){
        return categoryService.getCategory(id);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('USER')")
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public Category createCategory(@RequestBody Category category){
        System.out.println("Values be like: " + category.toString());
        return categoryService.saveCategory(category);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN') or hasAuthority('USER')")
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Category editCategory(@PathVariable Long id, @RequestBody Category category){
        return categoryService.editCategory(id,category);
    }

    @PostMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Category deleteCategory(@PathVariable Long id){
        return categoryService.disbaleCategory(id);
    }

}
