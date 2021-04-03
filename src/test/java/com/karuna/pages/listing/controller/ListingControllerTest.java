package com.karuna.pages.listing.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;
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
public class ListingControllerTest {

    @InjectMocks
    ListingController listingController;

    @Mock
    private ListingService listingService;

    private Listing stubListing(){

        Category category1 = new Category(1L,"Education",1);
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbom",1,roleList);
        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,appUser,category1);

        return listing;

    }

    @Test
    void getAllTest() {
        List<Listing> allListings = Arrays.asList(stubListing());

        when(listingService.getAllListings()).thenReturn(allListings);

        Collection<Listing> result = listingController.getAll();

        ArrayList<Listing> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).getAllListings();
    }

    @Test
    void getAllByCategoryTest() {
        List<Listing> allListings = Arrays.asList(stubListing());
        Category category1 = new Category(1L,"Education",1);
        when(listingService.getAllListingsByCategory(any(Category.class))).thenReturn(allListings);

        Collection<Listing> result = listingController.getAllByCategory(category1);

        ArrayList<Listing> resultList = new ArrayList<>(result);

        assertThat(result.size()).isEqualTo(1);

        assertThat(resultList.get(0).getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).getAllListingsByCategory(any(Category.class));
    }

    @Test
    void getListingTest() {

        when(listingService.getListing(1L)).thenReturn(stubListing());

        Listing result = listingController.getListing(1L);

        assertThat(result.getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).getListing(anyLong());
    }

    @Test
    void createListingTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(listingService.saveListing(any(Listing.class))).thenReturn(stubListing());

        Listing response = listingController.createListing(stubListing());

        assertThat(response.getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).saveListing(any(Listing.class));
    }

    @Test
    void editListingTest() {

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(listingService.editListing(any(Listing.class))).thenReturn(stubListing());

        Listing response = listingController.editListing(stubListing());

        assertThat(response.getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).editListing(any(Listing.class));
    }

    @Test
    void deleteListingTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(listingService.deleteListing(anyLong())).thenReturn(stubListing());

        Listing response = listingController.deleteListing(1L);

        assertThat(response.getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).deleteListing(anyLong());
    }

    @Test
    void approveListingTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(listingService.approveListing(anyLong())).thenReturn(stubListing());

        Listing response = listingController.approveListing(1L);

        assertThat(response.getListingname()).isEqualTo(stubListing().getListingname());

        verify(listingService, times(1)).approveListing(anyLong());
    }
}