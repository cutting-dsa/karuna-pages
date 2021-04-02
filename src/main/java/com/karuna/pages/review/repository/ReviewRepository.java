package com.karuna.pages.review.repository;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Collection<Review> findAllByListing(Listing listing);

    Review getReviewById(Long id);
}
