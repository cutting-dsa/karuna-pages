package com.karuna.pages.question.service;

import com.karuna.pages.core.exceptions.ResourceNotFoundException;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.repository.AnswerRepository;
import com.karuna.pages.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class AnswerServiceImplementation implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

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

    @Override
    public Collection<Answer> getQuestionAnswers(Long id) {
        Question question = questionRepository.findQuestionById(id);
        if (question == null) throw new ResourceNotFoundException("Question with id " + id + " not found");
        return answerRepository.getAnswerByQuestionId(id);
    }

    public Question mostAnsweredQuestion() {
        Optional<Question> popularQuestion = QuestionAnswerUtil.getMostAnsweredQuestion.apply(answerRepository.findAll(), 1);
        return popularQuestion.orElse(null);
    }

    public List<Question> mostAnsweredQuestions() {
        return QuestionAnswerUtil.computeMostAnsweredQuestions.apply(answerRepository.findAll(), 3);
    }
}
