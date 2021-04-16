package com.karuna.pages.reports;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionAnswerFactory {
    public static Answer createAnswer(String answerText, Question question, AppUser user) {
        Answer answer = new Answer();
        answer.setAnswer(answerText);
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setCreated_at(new Date());
        return answer;
    }

    public static Question createQuestion(String questionText, AppUser user) {
        Question question = new Question();
        question.setName(questionText);
        question.setCategory(createCategory());
        question.setUser(user);
        question.setAnswers(null);
        return question;
    }

    public static Category createCategory() {
        Category category = new Category();
        category.setName("Category 1");
        category.setActive(1);
        return category;
    }

    public static AppUser createUser(String username, String firstName, String lastName) {
        Role role = new Role(2L, "User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        return new AppUser(1L, username, firstName, lastName, "secret", 1, roleList);
    }
}
