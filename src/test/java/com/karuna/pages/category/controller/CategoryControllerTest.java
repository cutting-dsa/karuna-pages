package com.karuna.pages.category.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.service.CategoryService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class CategoryControllerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    private CategoryService categoryService;


    @Test
    void getAllTest() {
        Category category = new Category(1L,"Retailware",1);

        List<Category> allCategories = Arrays.asList(category);

       when(categoryService.getAllCategories()).thenReturn(allCategories);

        Collection<Category> result = categoryController.getAll();

        ArrayList<Category> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getName()).isEqualTo(category.getName());

        verify(categoryService, times(1)).getAllCategories();

         }

    @Test
    void getOneTest() {
        Category category = new Category(1L,"Retailware",1);

        when(categoryService.getCategory(1L)).thenReturn(category);

        Category result = categoryController.getOne(1L);

        assertThat(result.getName()).isEqualTo(category.getName());

        verify(categoryService, times(1)).getCategory(anyLong());
    }

    @Test
    void createCategoryTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category category = new Category(1L,"Retailware",1);

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        Category response = categoryController.createCategory(category);

        assertThat(response.getName()).isEqualTo(category.getName());

        verify(categoryService, times(1)).saveCategory(any(Category.class));

    }

    @Test
    void editCategoryTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category category = new Category(1L,"Education",1);

        when(categoryService.editCategory(any(Category.class))).thenReturn(category);

        Category response = categoryController.editCategory(category);

        assertThat(response.getName()).isEqualTo(category.getName());

        verify(categoryService, times(1)).editCategory(any(Category.class));
    }

    @Test
    void deleteCategoryTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category category = new Category(1L,"Education",1);

        when(categoryService.disbaleCategory(anyLong())).thenReturn(category);

        Category response = categoryController.deleteCategory(1L);

        assertThat(response.getName()).isEqualTo(category.getName());

        verify(categoryService, times(1)).disbaleCategory(anyLong());
    }
}