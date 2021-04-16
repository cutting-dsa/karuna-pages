package com.karuna.pages.question.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.service.AnswerService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
class AnswerControllerTest {

    @InjectMocks
    AnswerController answerController;

    @Mock
    AnswerService answerService;

    private AppUser stubUser() {
        Role role = new Role(2L, "User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        return new AppUser(1L, "Ruvimbom", "Ruvimbo", "Ruvimbo", "Ruvimbom", 1, roleList);
    }

    private Answer stubAnswer() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 59, 59, 59);
        Date qDate = calendar.getTime();
        return new Answer(1L, "Yes they are available", stubUser(), stubQuestion(), qDate);
    }

    private Question stubQuestion() {

        Category category1 = new Category(1L, "Education", 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 59, 59, 59);
        Date qDate = calendar.getTime();
        Question question = new Question(1L, "Which programs are offered at Maharishi", true, category1, stubUser(),null, qDate);

        return question;

    }

    @Test
    void answerQuestion() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(answerService.save(any(Answer.class))).thenReturn(stubAnswer());

        Answer response = answerController.answerQuestion(stubAnswer());

        assertThat(response.getAnswer()).isEqualTo(stubAnswer().getAnswer());

        verify(answerService, times(1)).save(any(Answer.class));
    }

    @Test
    void getQuestionAnswers() {
        List<Answer> allAnswers = Arrays.asList(stubAnswer());

        when(answerService.getQuestionAnswers(anyLong())).thenReturn(allAnswers);

        Collection<Answer> result = answerController.getQuestionAnswers(1L);

        ArrayList<Answer> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getAnswer()).isEqualTo(stubAnswer().getAnswer());

        verify(answerService, times(1)).getQuestionAnswers(anyLong());
    }
}