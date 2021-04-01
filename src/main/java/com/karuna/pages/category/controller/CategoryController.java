package com.karuna.pages.category.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(path = "/getone", produces = "application/json")
    public Category getOne(Long id){
        return categoryService.getCategory(id);
    }
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Category createCategory(@RequestBody Category category){
        System.out.println("Values be like: " + category.toString());
        return categoryService.saveCategory(category);
    }

    @PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
    public Category editCategory(@RequestBody Category category){
        return categoryService.editCategory(category);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public Category deleteCategory(@RequestBody Long id){
        return categoryService.disbaleCategory(id);
    }

}
