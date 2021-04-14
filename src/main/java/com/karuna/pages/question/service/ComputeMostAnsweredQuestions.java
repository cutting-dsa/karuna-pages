package com.karuna.pages.question.service;

import com.karuna.pages.question.model.Question;

import java.util.List;

@FunctionalInterface
public interface ComputeMostAnsweredQuestions {
    List<Question> compute();
}
