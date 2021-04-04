package com.karuna.pages.listing.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.repository.ListingRepository;
import com.karuna.pages.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ListingServiceImplementation implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Collection<Listing> getAllListings() {
        return listingRepository.findAllByActive(1);
    }

    @Override
    public Collection<Listing> getAllListingsByCategory(Category category) {
        return listingRepository.findAllByCategoryAndActive(category,1);
    }

    @Override
    public Listing getListing(Long id) {
        return listingRepository.getListingById(id);
    }

    @Override
    public Listing saveListing(Listing listing) {
        return listingRepository.save(listing);
    }

    @Override
    public Listing editListing(Listing listing) {

        return listingRepository.save(listing);
    }

    @Override
    public Listing deleteListing(Long id) {
        Listing listing =  listingRepository.getListingById(id);
         listing.setActive(0);
        listingRepository.save(listing);
        return listing;
    }

    @Override
    public Listing approveListing(Long id) {
        Listing listing =  listingRepository.getListingById(id);
        listing.setApproved(1);
        listingRepository.save(listing);
        return listing;
    }

    @Override
    public Collection<Listing> getListingByUser(AppUser id) {
        return listingRepository.findAllByListinguser(id);
    }

    @Override
    public Listing searchListing(String keyword) {
        System.out.println("Result is " + listingRepository.getListingByListingname(keyword));
        return listingRepository.getListingByListingname(keyword);
    }
}
