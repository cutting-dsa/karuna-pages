package com.karuna.pages.reports.utilities;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.user.model.AppUser;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


public class ReviewReports {


    public static final BiFunction<List<Listing>, Long, List<AppUser>>

            TOP_K_REVIEWERS = ((listings, k) ->
            listings.stream()
                    .flatMap(listing -> listing.getReviewList().stream())
                    .collect(Collectors.groupingBy(review -> review.getReviewUser()))
                    .entrySet()
                    .stream()
                    .sorted((u1, u2) -> u2.getValue().size() - u1.getValue().size())
                    .limit(k)
                    .map(users -> users.getKey())
                    .collect(Collectors.toList()));

    public static TriFunction<List<Review>, Long, Double, List<String>> reviewCommentsOfLowestRatingListings =
            (reviews, topK, rating) ->
                    reviews.stream()
                            .filter(review -> review.getRating() < rating)
                            .map(Review::getComment)
                            .limit(topK)
                            .collect(Collectors.toList());


    public static BiFunction<List<Review>, Long, List<AppUser>> ownersOfListingsWithLowReviews =
            (reviews, rating) ->
                    reviews.stream()
                            .filter(review -> review.getRating() < rating)
                            .map(Review::getListing)
                            .map(Listing::getListinguser)
                            .distinct()
                            .collect(Collectors.toList());
}
