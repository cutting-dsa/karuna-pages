package com.karuna.pages.reports.utilities;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.reports.Factory;
import com.karuna.pages.user.model.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class ReviewReportsTest {

    Factory factory = new Factory();

    @Test
    public void topKReviewersTest(){

        List<AppUser> result = ReviewReports.TOP_K_REVIEWERS.apply(factory.listings(),1l);
        assertThat(result,containsInAnyOrder(factory.stubUser()));
        Assert.assertEquals(1,result.size());

    }

    @Test
    public void lowRatedCategoryTest(){

        Optional<Category> result = ReviewReports.LOW_RATED_CATEGORY.apply(factory.listings());

        Assert.assertEquals(factory.stubCategory().getName(),result.get().getName());

    }

    @Test
    public void mostAnsweringUserTest(){

        Optional<AppUser> result = ReviewReports.MOST_ANSWERING_USER_PER_CATEGORY
                .apply(factory.questions(),factory.stubCategory());

        Assert.assertEquals(factory.stubUser2().getFirstName(),result.get().getFirstName());

    }

}