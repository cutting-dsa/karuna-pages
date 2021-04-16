package com.karuna.pages.reports.utilities;

import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.user.model.AppUser;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class ListingReports {

    public static final BiFunction<List<Listing>,Long, List<AppUser>>  TOP_K_USER_WITH_BEST_RATED_LISTING =
            ( (listings, k) -> listings.stream().flatMap(l -> l.getReviewList().stream())
            .collect(Collectors.groupingBy(Review::getListing, Collectors.averagingInt((r) -> r.getRating())))
                    .entrySet().stream()
                    .sorted((e1,e2) -> e1.getValue().compareTo(e2.getValue()))
                    .limit(k)
                    .map(e -> e.getKey())
                    .map(l -> l.getListinguser())
                    .collect(Collectors.toList())
            );
}
