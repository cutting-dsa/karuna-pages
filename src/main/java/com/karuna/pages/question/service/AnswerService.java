package com.karuna.pages.question.service;

import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.*;

@Service
public interface AnswerService {
    Collection<Answer> getAllAnswers();
    Collection<Answer> getAllAnswersByQuestion(Long id);

    Answer save(Answer answer);

    Collection<Answer> getQuestionAnswers(Long id);

    Question mostAnsweredQuestion();

    List<Question> mostAnsweredQuestions(Integer count);
}
