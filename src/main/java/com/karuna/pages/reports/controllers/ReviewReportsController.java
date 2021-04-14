package com.karuna.pages.reports.controllers;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;

import static com.karuna.pages.reports.utilities.ReviewReports.*;

import com.karuna.pages.reports.utilities.ReviewReports;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.service.ReviewService;
import com.karuna.pages.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path = "/reviewreports")
public class ReviewReportsController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "/bestreviewers/{number}", produces = "application/json")
    public Collection<AppUser> getTopReviewers(@PathVariable Long number) {

        System.out.println("Number: " + number);
        List<Listing> allListings = listingService.getAllListings();
        allListings.forEach(listing -> {
            List<Review> allReviews = reviewService.getAllReviewsByListing(listing);
            listing.setReviewList(allReviews);
        });


        return TOP_K_REVIEWERS.apply(allListings, number);
    }

    @GetMapping(path = "/lowest-rating-comments/{count}/{rating}")
    public Collection<String> getCommentsOfLowestRatings(@PathVariable Long count, @PathVariable Double rating) {
        return reviewService.getCommentsOfLowestRatings(count, rating);
    }

}
