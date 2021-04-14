//package com.karuna.pages.listing.model;
//
//import com.karuna.pages.category.model.Category;
//import com.karuna.pages.role.model.Role;
//import com.karuna.pages.user.model.AppUser;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//class ListingTest {
//
//    @Mock
//    Listing listing;
//
//    private Listing stubListing(){
//
//        Category category1 = new Category(1L,"Education",1);
//        Role role = new Role(2L,"User");
//        List<Role> roleList = new ArrayList<>();
//        roleList.add(role);
//        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
//        Listing listing = new Listing(1L,"Maharishi",1,"newAddress",123.2,321.1,"banner.png","icon.png",0,1,appUser,category1);
//
//        return listing;
//
//    }
//    private Listing stubSavedListing(){
//
//        Category category1 = new Category(1L,"Education",1);
//        Role role = new Role(2L,"User");
//        List<Role> roleList = new ArrayList<>();
//        roleList.add(role);
//        AppUser appUser = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);
//        Listing listing = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,appUser,category1);
//
//        return listing;
//
//    }
//
//    @Test
//    void editListingTest() {
//      stubSavedListing().editListing(stubListing());
//
//        verify(listing, times(1)).editListing(any(Listing.class));
//        //verify(listing, times(1)).setListingname(anyString());
//        //verify(listing, times(1)).setAddress(anyString());
//    }
//}