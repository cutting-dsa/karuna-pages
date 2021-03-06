package com.karuna.pages.reports.utilities;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.reports.Factory;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.user.model.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ReviewReportsTest {

    Factory factory = new Factory();

    OwnerFactory ownerFactory = new OwnerFactory();

    @Test
    public void topKReviewersTest(){

        List<AppUser> result = ReviewReports.TOP_K_REVIEWERS.apply(factory.listings(),1l);
        assertThat(result,containsInAnyOrder(factory.stubUser()));
        assertEquals(1,result.size());

    }

    @Test
    public void lowRatedCategoryTest(){

        Optional<Category> result = ReviewReports.LOW_RATED_CATEGORY.apply(factory.listings());

        assertEquals(factory.stubCategory().getName(),result.get().getName());

    }

    @Test
    public void mostAnsweringUserTest(){

        Optional<AppUser> result = ReviewReports.MOST_ANSWERING_USER_PER_CATEGORY
                .apply(factory.questions(),factory.stubCategory());

        assertEquals(factory.stubUser2().getFirstName(),result.get().getFirstName());

    }


    @Test
    public void reviewCommentsOfLowestRatingListingsTest() {
        List<Review> reviewList = factory.getReviewList();
        List<String> reviewComments = ReviewReports.reviewCommentsOfLowestRatingListings.apply(reviewList,3L,3.5);
        assertEquals(1,reviewComments.size());
        assertThat(reviewComments, containsInAnyOrder("This is my another"));
    }

    @Test
    public void ownersOfListingsWithLowReviewsTest() {
        List<Review> reviewList = ownerFactory.getReviewList();
        List<AppUser> owners = ReviewReports.ownersOfListingsWithLowReviews.apply(reviewList,3.5);
        assertEquals(1,owners.size());
        assertEquals("Ruvimbom", owners.get(0).getUsername());
    }

}