package com.karuna.pages.review.repository;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByListingAndStatus(Listing listing, int status);

    Collection<Review> findAllByStatus(int status);

    Review getReviewById(Long id);
}
