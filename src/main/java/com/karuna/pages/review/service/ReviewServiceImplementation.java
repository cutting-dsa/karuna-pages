package com.karuna.pages.review.service;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Collection<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public Collection<Review> getAllReviewsByListing(Listing listing) {
        return reviewRepository.findAllByListing(listing);
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.getReviewById(id);
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review editReview(Review review) {

        return reviewRepository.save(review);
    }

    @Override
    public Review deleteReview(Long id) {
        Review review =  reviewRepository.getReviewById(id);
         review.setStatus(0);
        reviewRepository.save(review);
        return review;
    }

}
