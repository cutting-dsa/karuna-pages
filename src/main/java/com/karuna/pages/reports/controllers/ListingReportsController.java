package com.karuna.pages.reports.controllers;

import com.karuna.pages.category.model.Category;
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

@RestController
@RequestMapping(path = "/listingreports")
public class ListingReportsController {

    @Autowired
    private ListingService listingService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "/bestperformadmin/{number}", produces = "application/json")
    public List<AppUser> getUserWhoseListingIsBestReviewed(@PathVariable Long number){
        return listingService.getUserWhoseListingIsBestReviewed(number);
    }

    @GetMapping(path = "/userwithlowestaveragerating/{number}", produces = "application/json")
    public List<AppUser> getUsersWhoGaveLeastAverageRatingOnListings(@PathVariable Long number){
        return listingService.getUsersWhoGaveLeastAverageRatingOnListings(number);
    }

    @GetMapping(path = "/mostpopularcategory/{limit}", produces = "application/json")
    public Collection<Category> getMostPopularCategories(@PathVariable Long limit){
        return listingService.getMostPopularCategory(limit);
    }
}
