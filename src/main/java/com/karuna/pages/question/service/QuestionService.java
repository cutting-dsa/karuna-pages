package com.karuna.pages.question.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface QuestionService {
    Collection<Question> getAllQuestions();

    Collection<Question> getQuestions(Category category);

    Question getQuestion(Long id);

    Question saveQuestion(Question question);

    Question editQuestion(Question question);

    Question disableQuestion(Long id);
}
