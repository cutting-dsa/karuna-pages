package com.karuna.pages.category.controller;

import com.karuna.pages.category.service.CategoryService;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CategoryController categoryController;
    
    @Before("")
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .build();
    }

    @Test
    void getAll() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/category/")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Education")));
    }

    @Test
    void getOne() {
    }

    @Test
    void createCategory() {
    }

    @Test
    void editCategory() {
    }

    @Test
    void deleteCategory() {
    }
}