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
import static org.junit.jupiter.api.Assertions.*;

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

        Assert.assertEquals(Optional.of(factory.stubCategory()),result);

    }

}