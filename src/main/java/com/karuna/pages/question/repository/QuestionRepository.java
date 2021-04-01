package com.karuna.pages.question.repository;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();

    List<Question> findAllByCategory(Category category);

    Question findQuestionById(Long id);

    Question answerQuestion(Question question, Answer answer);
}
