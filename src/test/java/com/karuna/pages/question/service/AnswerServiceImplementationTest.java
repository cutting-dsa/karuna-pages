package com.karuna.pages.question.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.repository.AnswerRepository;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AnswerServiceImplementationTest {

    @Mock
    AnswerRepository answerRepository;

    @InjectMocks
    AnswerServiceImplementation answerService;

    private Answer stubAnswer(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,11,31,59,59,59);
        Date qDate = calendar.getTime();
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
        Answer answer = new Answer(1L,"Yes they are available",appUser,stubQuestion(),qDate);

        return answer;

    }
    private Question stubQuestion(){

        Category category1 = new Category(1L,"Education",1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,11,31,59,59,59);
        Date qDate = calendar.getTime();
        Question question = new Question(1L,"Which programs are offered at Maharishi",true,category1,qDate);

        return question;

    }

    @Test
    void getAllAnswersTest() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(stubAnswer());


        when(answerRepository.findAll()).thenReturn(answerList);

        Collection<Answer> answers = answerService.getAllAnswers();
        Assert.assertEquals(answerList.size(), answers.size());
        ArrayList<Answer> actualResult = new ArrayList<>(answers);
        Assert.assertEquals(answerList.get(0).getAnswer(), actualResult.get(0).getAnswer());
        verify(answerRepository, times(1)).findAll();
    }

    @Test
    void getAllAnswersByQuestionTest() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(stubAnswer());


        when(answerRepository.getAnswerByQuestionId(anyLong())).thenReturn(answerList);

        Collection<Answer> answers = answerService.getAllAnswersByQuestion(1L);
        Assert.assertEquals(answerList.size(), answers.size());
        ArrayList<Answer> actualResult = new ArrayList<>(answers);
        Assert.assertEquals(answerList.get(0).getAnswer(), actualResult.get(0).getAnswer());
        verify(answerRepository, times(1)).getAnswerByQuestionId(anyLong());
    }

    @Test
    void saveTest() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(stubAnswer());


        when(answerRepository.save(any(Answer.class))).thenReturn(stubAnswer());
        answerService.save(stubAnswer());

        verify(answerRepository, times(1)).save(any(Answer.class));
    }
}