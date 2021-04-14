package com.karuna.pages.review.service;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface ReviewService {

    Collection<Review> getAllReview();

    List<Review> getAllReviewsByListing(Listing listing);

    Review getReview(Long id);

    Review saveReview(Review review);

    Review editReview(Long id, Review review);

    Review deleteReview(Long id);

    List<String> getCommentsOfLowestRatings(Long count, Double rating);
}
