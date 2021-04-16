package com.karuna.pages.question.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface QuestionService {
    List<Question> getAllQuestions();

    Collection<Question> getQuestions(Long id);

    Question getQuestion(Long id);

    Question saveQuestion(Question question);

    Question editQuestion(Question question);

    Question disableQuestion(Long id);

    Collection<Question> getQuestionByCategory(Long id);
}
