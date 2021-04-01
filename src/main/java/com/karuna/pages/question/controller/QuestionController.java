package com.karuna.pages.question.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/question", produces = "application/json")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public Collection<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category")
    public Collection<Question> getAllQuestionByCategory(@RequestBody Category category) {
        return questionService.getQuestions(category);
    }

    @PostMapping(value = "/create", consumes = "application/json")
    public Question createQuestion(@RequestBody Question question) {
        return questionService.saveQuestion(question);
    }

    @GetMapping("/single-question")
    public Question getSingleQuestion(@RequestBody Long id) {
        return questionService.getQuestion(id);
    }

    @PutMapping("/update")
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.editQuestion(question);
    }

//    @PostMapping("/answer-question")
//    public Answer answerQuestion(@RequestBody Answer answer, Long id) {
//        Question question = questionService.getQuestion(id);
//        return questionService.
//    }
}
