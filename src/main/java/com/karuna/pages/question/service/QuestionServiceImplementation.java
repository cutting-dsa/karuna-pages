package com.karuna.pages.question.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.category.repository.CategoryRepository;
import com.karuna.pages.core.exceptions.ResourceNotFoundException;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class QuestionServiceImplementation implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Collection<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Collection<Question> getQuestions(Long id) {
        return questionRepository.findAllByCategoryId(id);
    }

    @Override
    public Question getQuestion(Long id) {
        return questionRepository.findQuestionById(id);
    }

    @Override
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question editQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question disableQuestion(Long id) {
        return null;
    }

    @Override
    public Collection<Question> getQuestionByCategory(Long id) {
        Category category = categoryRepository.getCategoryById(id);
        if (category == null) throw new ResourceNotFoundException("Question with id " + id + " not found");
        return questionRepository.findQuestionsByCategory(category);
    }
}
