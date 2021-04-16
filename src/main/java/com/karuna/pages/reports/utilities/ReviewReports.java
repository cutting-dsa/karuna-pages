package com.karuna.pages.reports.utilities;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.user.model.AppUser;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReviewReports {

    //BY RUVIMBO
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

    //BY RUVIMBO
    public static final Function<List<Listing>, Optional<Category>>
            LOW_RATED_CATEGORY = listings ->
            listings.stream()
                    .filter(listing -> listing.getAverageRating() <= 2)
                    .collect(Collectors.groupingBy(listing -> listing.getCategory()))
                    .entrySet()
                    .stream()
                    .sorted((c1, c2) -> c2.getValue().size() - c1.getValue().size())
                    .map(categoryListEntry -> categoryListEntry.getKey())
                    .collect(Collectors.toList())
                    .stream().findFirst();

    //BY RUVIMBO
    public static BiFunction<List<Question>, Category, Optional<AppUser>>
            MOST_ANSWERING_USER_PER_CATEGORY = (questions, category) ->
            questions.stream()
                    .filter(question -> question.getCategory().equals(category))
                    .flatMap(question -> question.getAnswers().stream())
                    .collect(Collectors.groupingBy(answer -> answer.getUser()))
                    .entrySet()
                    .stream()
                    .sorted((a1, a2) -> a2.getValue().size() - a1.getValue().size())
                    .map(appUserListEntry -> appUserListEntry.getKey())
                    .collect(Collectors.toList())
                    .stream().findFirst();


    //BY JOHNSTONE
    public static TriFunction<List<Review>, Long, Double, List<String>> reviewCommentsOfLowestRatingListings =
            (reviews, topK, rating) ->
                    reviews.stream()
                            .filter(review -> review.getRating() < rating)
                            .map(Review::getComment)
                            .limit(topK)
                            .collect(Collectors.toList());

    //BY JOHNSTONE
    public static BiFunction<List<Review>, Double, List<AppUser>> ownersOfListingsWithLowReviews =
            (reviews, rating) ->
                    reviews.stream()
                            .filter(review -> review.getRating() < rating)
                            .map(Review::getListing)
                            .map(Listing::getListinguser)
                            .collect(Collectors.toList());
}
