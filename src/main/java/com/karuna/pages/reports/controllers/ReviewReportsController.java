package com.karuna.pages.reports.controllers;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;

import static com.karuna.pages.reports.utilities.ReviewReports.*;

import com.karuna.pages.question.model.Question;
import com.karuna.pages.question.service.QuestionService;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.service.ReviewService;
import com.karuna.pages.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/reviewreports")
public class ReviewReportsController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private QuestionService questionService;

    //BY RUVIMBO
    @GetMapping(path = "/bestreviewers/{number}", produces = "application/json")
    public Collection<AppUser> getTopReviewers(@PathVariable Long number) {

        List<Listing> allListings = listingService.getAllListings();


        return TOP_K_REVIEWERS.apply(allListings, number);
    }

    //BY RUVIMBO
    @GetMapping(path = "/lowcategory", produces = "application/json")
    public Optional<Category> getLowRatedCategory() {

        List<Listing> allListings = listingService.getAllListings();

        return LOW_RATED_CATEGORY.apply(allListings);
    }

    //BY RUVIMBO
    @GetMapping(path = "/mostansweringuser/{category}", produces = "application/json")
    public Optional<AppUser> getMostAnsweringUser(@PathVariable Category category) {

        List<Question> allQuestions = questionService.getAllQuestions();

        return MOST_ANSWERING_USER_PER_CATEGORY.apply(allQuestions,category);
    }

    //BY JOHNSTONE
    @GetMapping(path = "/lowest-rating-comments/{count}/{rating}")
    public Collection<String> getCommentsOfLowestRatings(@PathVariable Long count, @PathVariable Double rating) {
        return reviewService.getCommentsOfLowestRatings(count, rating);
    }

    //BY JOHNSTONE
    @GetMapping(path = "/low-listing-owners/{rating}")
    public Collection<AppUser> getOwnersOfLowestRatings(@PathVariable Double rating) {
        return reviewService.getOwnersOfListingsWithLowReviews(rating);
    }
}
