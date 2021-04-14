package com.karuna.pages.listing.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.repository.ListingRepository;
import com.karuna.pages.review.model.Review;
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

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ListingServiceImplementationTest {

    @Mock
    ListingRepository listingRepository;

    @Mock
    Listing listing;

    @InjectMocks
    ListingServiceImplementation listingService;

    private Listing stubListing(){

        Category category1 = new Category(1L,"Education",1);
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        List<Review> reviewList = new ArrayList<>();
        reviewList.add(stubReview());
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,appUser,category1,reviewList);

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
    void getAllListingsTest() {

        List<Listing> listingListList = new ArrayList<>();
        listingListList.add(stubListing());


        when(listingRepository.findAllByActive(1)).thenReturn(listingListList);

        Collection<Listing> listings = listingService.getAllListings();
        Assert.assertEquals(listingListList.size(), listings.size());
        ArrayList<Listing> actualResult = new ArrayList<>(listings);
        Assert.assertEquals(listingListList.get(0).getListingname(), actualResult.get(0).getListingname());
        verify(listingRepository, times(1)).findAllByActive(1);
    }

    @Test
    void getAllListingsByCategoryTest() {
        List<Listing> listingListList = new ArrayList<>();
         listingListList.add(stubListing());
        Category category1 = new Category(1L,"Education",1);

        when(listingRepository.findAllByCategoryAndActive(category1,1)).thenReturn(listingListList);

        Collection<Listing> listings = listingService.getAllListingsByCategory(category1);
        Assert.assertEquals(listingListList.size(), listings.size());
        ArrayList<Listing> actualResult = new ArrayList<>(listings);
        Assert.assertEquals(listingListList.get(0).getListingname(), actualResult.get(0).getListingname());
        verify(listingRepository, times(1)).findAllByCategoryAndActive(category1,1);
    }

    @Test
    void getListingTest() {

        when(listingRepository.getListingById(anyLong())).thenReturn(stubListing());

        Listing actualResult = listingService.getListing(1L);

        Assertions.assertEquals("Jefferson",actualResult.getListingname());
        Assertions.assertEquals(1,actualResult.getActive());
    }

    @Test
    void saveListingTest() {

        listingService.saveListing(stubListing());

        verify(listingRepository, times(1)).save(any(Listing.class));
    }

    @Test
    void editListingTest() {
        when(listingRepository.getListingById(anyLong())).thenReturn(stubListing());

        listingService.editListing(1L,stubListing());

        verify(listingRepository, times(1)).save(any(Listing.class));
    }

    @Test
    void deleteListingTest() {
        when(listingRepository.getListingById(anyLong())).thenReturn(stubListing());
        when(listingRepository.save(any(Listing.class))).thenReturn(stubListing());

        Listing expected =  listingService.deleteListing(1L);

        Assertions.assertEquals(0,expected.getActive());
        verify(listingRepository, times(1)).getListingById(1L);
    }
    @Test
    void approveListingTest() {
        when(listingRepository.getListingById(anyLong())).thenReturn(stubListing());
        when(listingRepository.save(any(Listing.class))).thenReturn(stubListing());

        Listing expected =  listingService.approveListing(1L);

        Assertions.assertEquals(1,expected.getApproved());
        verify(listingRepository, times(1)).getListingById(1L);
    }

    @Test
    void getListingByUserTest() {
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
      AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);

        List<Listing> listingListList = new ArrayList<>();
        listingListList.add(stubListing());

        when(listingRepository.findAllByListinguser(any(AppUser.class))).thenReturn(listingListList);

        Collection<Listing> listings = listingService.getListingByUser(appUser);

        Assert.assertEquals(listingListList.size(), listings.size());
        ArrayList<Listing> actualResult = new ArrayList<>(listings);
        Assert.assertEquals(listingListList.get(0).getListingname(), actualResult.get(0).getListingname());
        verify(listingRepository, times(1)).findAllByListinguser(any(AppUser.class));

    }
}