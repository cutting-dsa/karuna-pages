package com.karuna.pages.question.service;

import com.karuna.pages.question.model.Answer;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface AnswerService {
    Collection<Answer> getAllAnswers();

    Answer save(Answer answer);
}
