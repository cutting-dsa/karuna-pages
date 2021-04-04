package com.karuna.pages.review.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.controller.ListingController;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.service.ReviewService;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ReviewControllerTest {

    @InjectMocks
    ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    private Listing stubListing(){

        Category category1 = new Category(1L,"Education",1);
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,appUser,category1);

        return listing;

    }
    private Review stubReview(){
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
        Review review = new Review(1L,"This is my comment",4,1,appUser,stubListing());

        return review;

    }

    @Test
    void getAllTest() {
        List<Review> allReviews = Arrays.asList(stubReview());

        when(reviewService.getAllReview()).thenReturn(allReviews);

        Collection<Review> result = reviewController.getAll();

        ArrayList<Review> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getComment()).isEqualTo(stubReview().getComment());

        verify(reviewService, times(1)).getAllReview();
    }

    @Test
    void getAllByListingTest() {
        List<Review> allReviews = Arrays.asList(stubReview());

        when(reviewService.getAllReviewsByListing(any(Listing.class))).thenReturn(allReviews);

        Collection<Review> result = reviewController.getAllByListing(stubListing());

        ArrayList<Review> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getComment()).isEqualTo(stubReview().getComment());

        verify(reviewService, times(1)).getAllReviewsByListing(any(Listing.class));
    }

    @Test
    void getReviewTest() {
        when(reviewService.getReview(1L)).thenReturn(stubReview());

        Review result = reviewController.getReview(1L);

        assertThat(result.getComment()).isEqualTo(stubReview().getComment());

        verify(reviewService, times(1)).getReview(anyLong());
    }

    @Test
    void createReviewTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(reviewService.saveReview(any(Review.class))).thenReturn(stubReview());

        Review response = reviewController.createReview(stubReview());

        assertThat(response.getComment()).isEqualTo(stubReview().getComment());

        verify(reviewService, times(1)).saveReview(any(Review.class));
    }

    @Test
    void editReviewTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(reviewService.editReview(any(Review.class))).thenReturn(stubReview());

        Review response = reviewController.editReview(stubReview());

        assertThat(response.getComment()).isEqualTo(stubReview().getComment());

        verify(reviewService, times(1)).editReview(any(Review.class));
    }

    @Test
    void deleteReviewTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(reviewService.deleteReview(anyLong())).thenReturn(stubReview());

        Review response = reviewController.deleteReview(1L);

        assertThat(response.getComment()).isEqualTo(stubReview().getComment());

        verify(reviewService, times(1)).deleteReview(anyLong());
    }
}