package com.karuna.pages.listing.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/listing")
public class ListingController {

    @Autowired
    private ListingService listingService;

    @GetMapping(path = "/", produces = "application/json")
    public Collection<Listing> getAll(){
        return listingService.getAllListings();
    }


    @GetMapping(path = "/bycategory", produces = "application/json")
    public Collection<Listing> getAllByCategory(@RequestBody Category category){
        return listingService.getAllListingsByCategory(category);
    }

    @GetMapping(path = "/getone", produces = "application/json")
    public Listing getListing(@RequestBody Long id){
        return listingService.getListing(id);
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Listing createListing(@RequestBody Listing listing){
        return listingService.saveListing(listing);
    }

    @PostMapping(value = "/edit", consumes = "application/json", produces = "application/json")
    public Listing editListing(@RequestBody Listing listing){
        return listingService.editListing(listing);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public Listing deleteListing(@RequestBody Long id){
        return listingService.deleteListing(id);
    }

    @PostMapping(value = "/approve", consumes = "application/json", produces = "application/json")
    public Listing approveListing(@RequestBody Long id){
        return listingService.approveListing(id);
    }
}
