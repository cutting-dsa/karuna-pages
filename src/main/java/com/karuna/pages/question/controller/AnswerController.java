package com.karuna.pages.question.controller;

import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/answer", produces = "application/json")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/create/")
    public Answer answerQuestion(@RequestBody Answer answer) {
        return answerService.save(answer);
    }

    @GetMapping("/question-answers/{id}")
    public Collection<Answer> getQuestionAnswers(@PathVariable Long id) {
        return answerService.getAllAnswersByQuestion(id);
    }
}
