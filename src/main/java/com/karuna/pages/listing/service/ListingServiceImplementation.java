package com.karuna.pages.listing.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ListingServiceImplementation implements ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Override
    public Collection<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    @Override
    public Collection<Listing> getAllListingsByCategory(Category category) {
        return listingRepository.findAllByCategory(category);
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
}
