package com.karuna.pages.review.service;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ReviewService {

    Collection<Review> getAllReview();

    Collection<Review> getAllReviewsByListing(Listing listing);

    Review getReview(Long id);

    Review saveReview(Review review);

    Review editReview(Long id,Review review);

    Review deleteReview(Long id);
}
