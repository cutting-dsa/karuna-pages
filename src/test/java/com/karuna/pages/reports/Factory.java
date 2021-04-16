package com.karuna.pages.reports;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;

import java.util.ArrayList;
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

        Category category1 = new Category(1L,"Education",1);
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview1());
        reviewList.add(stubReview2());
        reviewList.add(stubReview3());
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,stubUser(),category1,reviewList);

        return listing;

    }

    public Listing stubListing1(){

        Category category1 = new Category(1L,"Education",1);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview1());
        reviewList.add(stubReview2());
        reviewList.add(stubReview3());
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,stubUser(),category1,reviewList);

        return listing;

    }
    public Listing stubListing2(){

        Category category1 = new Category(1L,"Education",1);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview1());
        reviewList.add(stubReview2());
        reviewList.add(stubReview3());
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,stubUser(),category1,reviewList);

        return listing;

    }

    public Review stubReview1(){
        Review review = new Review(1L,"This is my comment",4,1,stubUser(),stubListing());

        return review;

    }

    public Review stubReview2(){
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
        Review review = new Review(1L,"This is my another",4,1,appUser,stubListing());

        return review;

    }

    public Review stubReview3(){
      Review review = new Review(1L,"This is my another",4,1,stubUser(),stubListing());

        return review;

    }

    public List<Listing> listings(){

        List<Listing> listings = new ArrayList<>();
        listings.add(stubListing());
        listings.add(stubListing1());
        listings.add(stubListing2());
        return  listings;
    }



}
