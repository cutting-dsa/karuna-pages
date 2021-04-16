package com.karuna.pages.reports.utilities;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.user.model.AppUser;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListingReports {

    public static final BiFunction<List<Listing>,Long, List<AppUser>>  TOP_K_USER_WITH_BEST_RATED_LISTING =
            ( (listings, k) -> listings.stream().flatMap(l -> l.getReviewList().stream())
            .collect(Collectors.groupingBy(Review::getListing, Collectors.averagingInt((r) -> r.getRating())))
                    .entrySet().stream()
                    .sorted((e1,e2) -> e2.getValue().compareTo(e1.getValue()))
                    .limit(k)
                    .map(e -> e.getKey())
                    .map(l -> l.getListinguser())
                    .collect(Collectors.toList())
            );

    public static final BiFunction<List<Listing>,Long, List<AppUser>>  USERS_WHO_GAVE_LEAST_AVERAGE_RATING_ON_LISTINGS =
            ( (listings, k) -> listings.stream().flatMap(l -> l.getReviewList().stream())
                    .collect(Collectors.groupingBy(Review::getReviewUser, Collectors.averagingInt((r) -> r.getRating())))
                    .entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .limit(k)
                    .map(e -> e.getKey())
                    .collect(Collectors.toList())

            );

    public static final Function<List<Listing>,List<Category>> CATEGORIES_FROM_LISTINGS =
            ( (listings) -> listings.stream().map(l -> l.getCategory())
                    .collect(Collectors.toList())

            );

    public static final Function<List<Question>,List<Category>> CATEGORIES_FROM_QUESTION =
            ( (questions) -> questions.stream().map(q -> q.getCategory())
                    .collect(Collectors.toList())

            );

    public static final TriFunction<List<Listing>,List<Question>, Long, List<Category>>  MOST_POPULAR_CATEGORY =
            ( (listings, questions, k) -> Stream.concat(CATEGORIES_FROM_LISTINGS.apply(listings).stream(), CATEGORIES_FROM_QUESTION.apply(questions).stream())
                    .collect(Collectors.groupingBy((c) -> c,Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())).limit(k).map(e -> e.getKey())
                    .collect(Collectors.toList())
            );

}
