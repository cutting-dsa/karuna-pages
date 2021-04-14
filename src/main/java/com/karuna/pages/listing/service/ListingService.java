package com.karuna.pages.listing.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.user.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface ListingService {

    List<Listing> getAllListings();
    Collection<Listing> searchListing(String keyword);

    Collection<Listing> getAllListingsByCategory(Category category);

    Listing getListing(Long id);

    Collection<Listing> getListingByUser(AppUser id);

    Listing saveListing(Listing listing);

    Listing editListing(Long id, Listing listing);

    Listing deleteListing(Long id);

    Listing approveListing(Long id);

}
