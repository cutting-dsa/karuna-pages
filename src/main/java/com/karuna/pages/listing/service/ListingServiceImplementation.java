package com.karuna.pages.listing.service;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.core.exceptions.BadRequestException;
import com.karuna.pages.core.exceptions.ResourceNotFoundException;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.repository.ListingRepository;
import com.karuna.pages.review.model.Review;
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
    public Listing editListing(Long id, Listing listing) {
        if(id == null) throw new BadRequestException("Listing id cannot be null");

        if(listing == null) return null;

        Listing savedListing = listingRepository.getListingById(id);

        if(savedListing == null) throw new ResourceNotFoundException("Review with id " + id + " not found");

        return  listingRepository.save(listing);
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
    public Collection<Listing> searchListing(String keyword) {
        System.out.println("Result is " + listingRepository.findListingByListingname(keyword));
        return listingRepository.searchListing(keyword);
    }
}
