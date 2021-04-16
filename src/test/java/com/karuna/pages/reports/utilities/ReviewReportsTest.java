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
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ReviewReportsTest {

    Factory factory = new Factory();

    OwnerFactory ownerFactory = new OwnerFactory();

    @Test
    public void topKReviewersTest(){

        List<AppUser> result = ReviewReports.TOP_K_REVIEWERS.apply(factory.listings(),1l);
        assertThat(result,containsInAnyOrder(factory.stubUser()));
        Assert.assertEquals(1,result.size());

    }

    @Test
    public void lowRatedCategoryTest(){

        Optional<Category> result = ReviewReports.LOW_RATED_CATEGORY.apply(factory.listings());

        Assert.assertEquals(Optional.of(factory.stubCategory()),result);

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
        List<AppUser> owners = reviewList.stream()
                .filter(review -> review.getRating() < 3.5)
                .map(Review::getListing)
                .map(Listing::getListinguser)
                .distinct()
                .collect(Collectors.toList());
                //ReviewReports.ownersOfListingsWithLowReviews.apply(reviewList,3.5);
        assertEquals(1,owners.size());
        assertEquals("Ruvimbom", owners.get(0).getUsername());
    }

}