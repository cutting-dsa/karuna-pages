package com.karuna.pages.review.controller;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "/", produces = "application/json")
    public Collection<Review> getAll(){
        return reviewService.getAllReview();
    }

    @GetMapping(path = "/bylisting", produces = "application/json")
    public Collection<Review> getAllByListing(@RequestBody Listing listing){
        return reviewService.getAllReviewsByListing(listing);
    }

    @GetMapping(path = "/getone", produces = "application/json")
    public Review getReview(@RequestBody Long id){
        return reviewService.getReview(id);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Review createReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }

    @PutMapping(value = "/{}", consumes = "application/json", produces = "application/json")
    public Review editReview(@PathVariable Long id, @RequestBody Review review){
        return reviewService.editReview(id,review);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public Review deleteReview(@RequestBody Long id){
        return reviewService.deleteReview(id);
    }
}
