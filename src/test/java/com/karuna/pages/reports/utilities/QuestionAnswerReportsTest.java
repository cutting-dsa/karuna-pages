package com.karuna.pages.reports.utilities;

import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.service.QuestionAnswerUtil;
import com.karuna.pages.reports.QuestionAnswerFactory;
import com.karuna.pages.user.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class QuestionAnswerReportsTest {

    List<Answer> answerList;
    List<Question> questionList;

    @BeforeEach
    public void setUp() {
        AppUser questionUser = QuestionAnswerFactory.createUser("johnolwamba", "John", "Snow");
        Question question1 = QuestionAnswerFactory.createQuestion("What is the capital of Kenya", questionUser);
        Question question2 = QuestionAnswerFactory.createQuestion("How to use IntelliJ",
                QuestionAnswerFactory.createUser("janeDoe", "Jane", "Doe"));

        Answer answer1 = QuestionAnswerFactory.createAnswer("Nairobi", question1,
                QuestionAnswerFactory.createUser("johnDoe", "John", "Doe"));
        Answer answer2 = QuestionAnswerFactory.createAnswer("Kigali", question1,
                QuestionAnswerFactory.createUser("johnDoe", "John", "Doe"));
        Answer answer3 = QuestionAnswerFactory.createAnswer("RTFM", question2,
                QuestionAnswerFactory.createUser("johnDoe", "John", "Doe"));
        Answer answer4 = QuestionAnswerFactory.createAnswer("Nairobi", question1,
                QuestionAnswerFactory.createUser("johnDoe", "John", "Doe"));
        answerList = Arrays.asList(answer1, answer2, answer3, answer4);
        questionList = Arrays.asList(question1, question2);
    }


    @Test
    void mostAnsweredQuestionTest() {
        Optional<Question> question = QuestionAnswerUtil.getMostAnsweredQuestion.apply(answerList);
        assertTrue(question.isPresent());
        Question unwrappedQuestion = question.get();
        assertEquals(questionList.get(0).getName(), unwrappedQuestion.getName());
    }


    @Test
    void mostAnsweredQuestionsTest() {
        List<Question> questions = QuestionAnswerUtil.computeMostAnsweredQuestions.apply(answerList, 2);
        assertTrue(questions.size() > 0);
        assertThat(questions, containsInAnyOrder(questionList.get(0), questionList.get(1)));
    }
}
