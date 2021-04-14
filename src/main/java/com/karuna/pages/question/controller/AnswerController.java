package com.karuna.pages.question.controller;

import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/answer", produces = "application/json")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @PostMapping("/")
    public Answer answerQuestion(@RequestBody Answer answer) {
        return answerService.save(answer);
    }

    @GetMapping("/question/{id}")
    public Collection<Answer> getQuestionAnswers(@PathVariable Long id) {
       return answerService.getQuestionAnswers(id);
    }

    @GetMapping("/most-popular-question")
    public Question mostPopularQuestion() {
        return answerService.mostAnsweredQuestion();
    }

    @GetMapping("/most-popular-question/{count}")
    public List<Question> mostPopularQuestion(@PathVariable int count) {
        return answerService.mostAnsweredQuestions(count);
    }
}
