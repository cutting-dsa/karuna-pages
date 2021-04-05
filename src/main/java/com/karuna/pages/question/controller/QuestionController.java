package com.karuna.pages.question.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.repository.CategoryRepository;
import com.karuna.pages.category.service.CategoryService;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.repository.QuestionRepository;
import com.karuna.pages.question.service.AnswerService;
import com.karuna.pages.question.service.QuestionService;
import com.karuna.pages.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/question", produces = "application/json")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/")
    public Collection<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{id}")
    public Collection<Question> getAllQuestionByCategory(@PathVariable Long id) {
        Category category = categoryService.getCategory(id);
        return questionRepository.findQuestionsByCategory(category);
    }

    @PostMapping(value = "/", consumes = "application/json")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping("/{id}")
    public Question getSingleQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id);
    }

    @PutMapping("/")
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.editQuestion(question);
    }

}
