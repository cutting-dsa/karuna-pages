package com.karuna.pages.question.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.repository.QuestionRepository;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class QuestionServiceImplementationTest {

    @Mock
    QuestionRepository questionRepository;

    @InjectMocks
    QuestionServiceImplementation questionService;

    private Question stubQuestion(){

        Category category1 = new Category(1L,"Education",1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,11,31,59,59,59);
        Date qDate = calendar.getTime();
        Question question = new Question(1L,"Which programs are offered at Maharishi",true,category1,stubUser(),qDate);

        return question;

    }

    @Test
    void getAllQuestionsTest() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(stubQuestion());


        when(questionRepository.findAll()).thenReturn(questionList);

        Collection<Question> questions = questionService.getAllQuestions();
        Assert.assertEquals(questionList.size(), questions.size());
        ArrayList<Question> actualResult = new ArrayList<>(questions);
        Assert.assertEquals(questionList.get(0).getName(), actualResult.get(0).getName());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void getQuestionsTest() {

        List<Question> questionList = new ArrayList<>();
        questionList.add(stubQuestion());


        when(questionRepository.findAllByCategoryId(anyLong())).thenReturn(questionList);

        Collection<Question> questions = questionService.getQuestions(1L);
        Assert.assertEquals(questionList.size(), questions.size());
        ArrayList<Question> actualResult = new ArrayList<>(questions);
        Assert.assertEquals(questionList.get(0).getName(), actualResult.get(0).getName());
        verify(questionRepository, times(1)).findAllByCategoryId(anyLong());
    }

    @Test
    void getQuestionTest() {
        when(questionRepository.findQuestionById(anyLong())).thenReturn(stubQuestion());

        Question actualResult = questionService.getQuestion(1L);

        Assertions.assertEquals("Which programs are offered at Maharishi",actualResult.getName());
        Assertions.assertEquals(1L,actualResult.getId());
    }

    @Test
    void saveQuestionTest() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(stubQuestion());


        when(questionRepository.save(any(Question.class))).thenReturn(stubQuestion());
        questionService.saveQuestion(stubQuestion());

        verify(questionRepository, times(1)).save(any(Question.class));

    }

    @Test
    void editQuestionTest() {
        questionService.editQuestion(stubQuestion());

        verify(questionRepository, times(1)).save(any(Question.class));
    }

    private AppUser stubUser() {
        Role role = new Role(2L, "User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        return new AppUser(1L, "Ruvimbom", "Ruvimbo", "Ruvimbo", "Ruvimbom", 1, roleList);
    }

}