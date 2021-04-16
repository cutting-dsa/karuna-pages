package com.karuna.pages.reports;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Answer;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Factory {

    public AppUser stubUser(){

        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
        return  appUser;
    }

    public AppUser stubUser2(){

        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Reviewer","AnotherReviewr","AnotherReviewr","AnotherReviewr",1,roleList);
        return  appUser;
    }

    public Listing stubListing(){


        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview1());
        reviewList.add(stubReview2());
        reviewList.add(stubReview3());
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,4,stubUser(),stubCategory2(),reviewList);

        return listing;

    }

    public Listing stubListing1(){

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview1());
        reviewList.add(stubReview2());
        reviewList.add(stubReview3());
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,2,stubUser(),stubCategory(),reviewList);

        return listing;

    }
    public Listing stubListing2(){

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview1());
        reviewList.add(stubReview2());
        reviewList.add(stubReview3());
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,stubUser(),stubCategory(),reviewList);

        return listing;

    }

    public Review stubReview1(){
        Review review = new Review(1L,"This is my comment",4,1,stubUser(),null);

        return review;

    }

    public Review stubReview2(){
        Review review = new Review(1L,"This is my another",4,1,stubUser(),null);

        return review;

    }

    public Review stubReview3(){
      Review review = new Review(1L,"This is my another",4,1,stubUser2(),null);

        return review;

    }

    public List<Listing> listings(){

        List<Listing> listings = new ArrayList<>();
        listings.add(stubListing());
        listings.add(stubListing1());
        listings.add(stubListing2());
        return  listings;
    }

public Category stubCategory(){

        return new Category(1L,"Education",1);
}

    public Category stubCategory2(){

        return new Category(2L,"Retailware",1);
    }

    private Question stubQuestion(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,11,31,59,59,59);
        Date qDate = calendar.getTime();
        Question question = new Question(1L,"Which programs are offered at Maharishi",true,stubCategory(),stubUser(),answers(),qDate);

        return question;

    }

    private Answer stubAnswer() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 59, 59, 59);
        Date qDate = calendar.getTime();
        Answer answer = new Answer(1L, "Yes they are available", stubUser2(), null, qDate);

        return answer;

    }

    private Answer stubAnswer2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 59, 59, 59);
        Date qDate = calendar.getTime();
        Answer answer = new Answer(1L, "Yes they are available", stubUser(), null, qDate);

        return answer;

    }

    private Answer stubAnswer3() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 31, 59, 59, 59);
        Date qDate = calendar.getTime();
        Answer answer = new Answer(1L, "Yes they are available", stubUser2(), null, qDate);

        return answer;

    }

    public List<Answer> answers(){

        List<Answer> answers = new ArrayList<>();
        answers.add(stubAnswer());
        answers.add(stubAnswer2());
        answers.add(stubAnswer3());

        return answers;
    }

    public List<Question> questions(){

        List<Question> questions = new ArrayList<>();
        questions.add(stubQuestion());

        return questions;
    }
}
