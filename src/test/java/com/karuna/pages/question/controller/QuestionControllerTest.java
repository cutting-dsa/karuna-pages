package com.karuna.pages.question.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.service.QuestionService;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class QuestionControllerTest {

    @InjectMocks
    QuestionController questionController;

    @Mock
    QuestionService questionService;

    private Question stubQuestion() {

        Category category1 = new Category(1L, "Education", 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 59, 59, 59);
        Date qDate = calendar.getTime();
        return new Question(1L, "Which programs are offered at Maharishi", true, category1, stubUser(), qDate);
    }

    @Test
    void getAllQuestionsTest() {
        List<Question> allQuestions = Arrays.asList(stubQuestion());

        when(questionService.getAllQuestions()).thenReturn(allQuestions);

        Collection<Question> result = questionController.getAllQuestions();

        ArrayList<Question> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getName()).isEqualTo(stubQuestion().getName());

        verify(questionService, times(1)).getAllQuestions();
    }

    @Test
    void getAllQuestionByCategoryTest() {
        List<Question> allQuestions = Arrays.asList(stubQuestion());

        when(questionService.getQuestionByCategory(anyLong())).thenReturn(allQuestions);

        Collection<Question> result = questionController.getAllQuestionByCategory(1L);

        ArrayList<Question> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getName()).isEqualTo(stubQuestion().getName());

        verify(questionService, times(1)).getQuestionByCategory(anyLong());
    }

    @Test
    void createQuestionTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(questionService.saveQuestion(any(Question.class))).thenReturn(stubQuestion());

        Question response = questionController.createQuestion(stubQuestion());

        assertThat(response.getName()).isEqualTo(stubQuestion().getName());

        verify(questionService, times(1)).saveQuestion(any(Question.class));

    }

    @Test
    void getSingleQuestionTest() {
        when(questionService.getQuestion(1L)).thenReturn(stubQuestion());

        Question result = questionController.getSingleQuestion(1L);

        assertThat(result.getName()).isEqualTo(stubQuestion().getName());

        verify(questionService, times(1)).getQuestion(anyLong());
    }

    @Test
    void updateQuestionTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(questionService.editQuestion(any(Question.class))).thenReturn(stubQuestion());

        Question response = questionController.updateQuestion(stubQuestion());

        assertThat(response.getName()).isEqualTo(stubQuestion().getName());

        verify(questionService, times(1)).editQuestion(any(Question.class));
    }

    private AppUser stubUser() {
        Role role = new Role(2L, "User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        return new AppUser(1L, "Ruvimbom", "Ruvimbo", "Ruvimbo", "Ruvimbom", 1, roleList);
    }
}