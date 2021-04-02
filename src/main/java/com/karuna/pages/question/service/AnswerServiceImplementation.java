package com.karuna.pages.question.service;

import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AnswerServiceImplementation implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Collection<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Collection<Answer> getAllAnswersByQuestion(Long id) {
        return answerRepository.getAnswerByQuestionId(id);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

}
