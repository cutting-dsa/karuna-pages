package com.karuna.pages.reports;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class QuestionAnswerReportsTest {

    List<Answer> answerList;

    @BeforeEach
    public void setUp() {

    }


    @Test
    void mostAnsweredQuestionTest() {

        //assertEquals(stubQuestion().getId(), popular.getId());
    }


//    @Test
//    void mostAnsweredQuestionsTest() {
//        when(answerService.mostAnsweredQuestions(1)).thenReturn(Arrays.asList(stubQuestion()));
//        List<Question> popularQuestions = answerService.mostAnsweredQuestions(1);
//        assertEquals(Arrays.asList(stubQuestion()).size(), popularQuestions.size());
//    }
}
