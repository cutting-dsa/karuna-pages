package com.karuna.pages.review.service;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.repository.ListingRepository;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ReviewServiceImplementation implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ListingRepository listingRepository;

    private int totalRating = 0;
    private int averageRating = 0;
    private int count = 0;

    @Override
    public Collection<Review> getAllReview() {
        return reviewRepository.findAllByStatus(1);
    }

    @Override
    public Collection<Review> getAllReviewsByListing(Listing listing) {
        return reviewRepository.findAllByListingAndStatus(listing,1);
    }

    @Override
    public Review getReview(Long id) {
        return reviewRepository.getReviewById(id);
    }

    @Override
    public Review saveReview(Review review) {
        //Added average rating calculation
        totalRating = 0;
        averageRating = 0;
        count = 0;
        Review review1 = reviewRepository.save(review);
        Listing listing = listingRepository.getListingById(review.getListing().getId());
        Collection<Review>  Reviews = reviewRepository.findAllByListingAndStatus(listing,1);
        Reviews.forEach(e -> {
            totalRating += e.getRating();
            count++;
            System.out.println("totalRating: " + totalRating);
            System.out.println("count: " + count);
        });
        averageRating = totalRating/count;
        System.out.println("count: " + averageRating);
        listing.setAverageRating(averageRating);
        listingRepository.save(listing);
        return review1;
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
