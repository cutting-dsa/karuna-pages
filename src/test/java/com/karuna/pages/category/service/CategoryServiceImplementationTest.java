package com.karuna.pages.category.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@ExtendWith(SpringExtension.class)

public class CategoryServiceImplementationTest {

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImplementation categoryService;

    @Test
    void getAllCategoriesTest() {

        List<Category> categoryList = new ArrayList<>();
        Category category1 = new Category(1L,"Education",1);
        Category category2 = new Category(2L,"Retailware",1);
        categoryList.add(category1);
        categoryList.add(category2);

        when(categoryRepository.findAllByActive(1)).thenReturn(categoryList);

        Collection<Category> categories = categoryService.getAllCategories();
        Assert.assertEquals(categoryList.size(), categories.size());
                ArrayList<Category> actualResult = new ArrayList<>(categories);
        Assert.assertEquals(categoryList.get(0).getName(), actualResult.get(0).getName());
        verify(categoryRepository, times(1)).findAllByActive(1);
    }

    @Test
    void getCategoryTest() {
        Category category1 = new Category(1L,"Education",1);
        when(categoryRepository.getCategoryById(anyLong())).thenReturn(category1);

        Category actualResult = categoryService.getCategory(1L);

        Assertions.assertEquals("Education",actualResult.getName());
        Assertions.assertEquals(1,actualResult.getActive());
    }

    @Test
    void saveCategoryTest() {

        Category category1 = new Category(1L,"Education",1);
        when(categoryRepository.getCategoryById(anyLong())).thenReturn(category1);
        categoryService.saveCategory(category1);

        verify(categoryRepository, times(1)).save(category1);
    }

    @Test
    void editCategoryTest() {
        Category category1 = new Category(1L,"Education",1);
        when(categoryRepository.getCategoryById(anyLong())).thenReturn(category1);
        categoryService.editCategory(1L,category1);

        verify(categoryRepository, times(1)).save(category1);
    }

    @Test
    void disbaleCategoryTest() {

        Category category1 = new Category(1L,"Education",1);
        when(categoryRepository.getCategoryById(anyLong())).thenReturn(category1);
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category expected =  categoryService.disbaleCategory(1L);

        Assertions.assertEquals(0,expected.getActive());
        verify(categoryRepository, times(1)).getCategoryById(1L);

    }
}