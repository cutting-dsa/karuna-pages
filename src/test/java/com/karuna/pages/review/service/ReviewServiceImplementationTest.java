package com.karuna.pages.review.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.repository.ListingRepository;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.review.repository.ReviewRepository;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ReviewServiceImplementationTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    ListingRepository listingRepository;

    @InjectMocks
    ReviewServiceImplementation reviewService;

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
    void getAllReviewTest() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview());


        when(reviewRepository.findAllByStatus(anyInt())).thenReturn(reviewList);

        Collection<Review> reviews = reviewService.getAllReview();
        Assert.assertEquals(reviewList.size(), reviews.size());
        ArrayList<Review> actualResult = new ArrayList<>(reviews);
        Assert.assertEquals(reviewList.get(0).getComment(), actualResult.get(0).getComment());
        verify(reviewRepository, times(1)).findAllByStatus(1);
    }

    @Test
    void getAllReviewsByListingTest() {
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview());


        when(reviewRepository.findAllByListingAndStatus(any(Listing.class),anyInt())).thenReturn(reviewList);

        Collection<Review> reviews = reviewService.getAllReviewsByListing(stubListing());
        Assert.assertEquals(reviewList.size(), reviews.size());
        ArrayList<Review> actualResult = new ArrayList<>(reviews);
        Assert.assertEquals(reviewList.get(0).getComment(), actualResult.get(0).getComment());
        verify(reviewRepository, times(1)).findAllByListingAndStatus(any(Listing.class),anyInt());
    }

    @Test
    void getReviewTest() {
        when(reviewRepository.getReviewById(anyLong())).thenReturn(stubReview());

        Review actualResult = reviewService.getReview(1L);

        Assertions.assertEquals("This is my comment",actualResult.getComment());
        Assertions.assertEquals(1,actualResult.getStatus());
    }

    @Test
    void saveReviewTest() {

        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview());

        when(listingRepository.getListingById(anyLong())).thenReturn(stubListing());
        when(reviewRepository.getReviewById(anyLong())).thenReturn(stubReview());
        when(reviewRepository.findAllByListingAndStatus(any(Listing.class),anyInt())).thenReturn(reviewList);
        reviewService.saveReview(stubReview());

        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void editReviewTest() {
        when(reviewRepository.getReviewById(anyLong())).thenReturn(stubReview());
        reviewService.editReview(1L,stubReview());

        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    void deleteReviewTest() {
        when(reviewRepository.getReviewById(anyLong())).thenReturn(stubReview());
        when(reviewRepository.save(any(Review.class))).thenReturn(stubReview());

        Review expected =  reviewService.deleteReview(1L);

        Assertions.assertEquals(0,expected.getStatus());
        verify(reviewRepository, times(1)).getReviewById(1L);
    }
}