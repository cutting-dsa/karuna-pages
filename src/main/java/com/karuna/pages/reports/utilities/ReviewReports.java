package com.karuna.pages.reports.utilities;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.user.model.AppUser;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


public class ReviewReports {


public static final BiFunction<List<Listing>,Long, List<AppUser>>

    TOP_K_REVIEWERS = ( (listings, k) ->
        listings.stream()
        .flatMap(listing ->   listing.getReviewList().stream())
        .collect(Collectors.groupingBy(review -> review.getReviewUser()))
        .entrySet()
        .stream()
        .sorted((u1,u2) -> u2.getValue().size() - u1.getValue().size())
        .limit(k)
        .map(users -> users.getKey())
        .collect(Collectors.toList())

            );
}
