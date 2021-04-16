package com.karuna.pages.reports.controllers;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;
import com.karuna.pages.review.service.ReviewService;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

import static com.karuna.pages.reports.utilities.ListingReports.TOP_K_USER_WITH_BEST_RATED_LISTING;

@RestController
@RequestMapping(path = "/listingreports")
public class ListingReportsController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "/bestperformadmin/{number}", produces = "application/json")
    public List<AppUser> getUserWhoseListingIsBestReviewed(@PathVariable Long number){

        List<Listing> allListings = listingService.getAllListings();

        /*allListings.forEach(listing -> {
            List<Review> allReviews = reviewService.getAllReviewsByListing(listing);
            listing.setReviewList(allReviews);
        });*/


        return TOP_K_USER_WITH_BEST_RATED_LISTING.apply(allListings,number);
    }
}
