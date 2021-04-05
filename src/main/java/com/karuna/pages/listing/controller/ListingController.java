package com.karuna.pages.listing.controller;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.listing.service.ListingService;
import com.karuna.pages.user.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/listing")
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


    @GetMapping(path = "/{id}", produces = "application/json")
    public Listing getListing(@PathVariable Long id){
        return listingService.getListing(id);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN, USER')")
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Listing createListing(@RequestBody Listing listing){
        return listingService.saveListing(listing);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN, USER')")
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public Listing editListing(@PathVariable Long id,@RequestBody Listing listing){
        return listingService.editListing(id,listing);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN, USER')")
    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public Listing deleteListing(@RequestBody Long id){
        return listingService.deleteListing(id);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping(value = "/approve", consumes = "application/json", produces = "application/json")
    public Listing approveListing(@RequestBody Long id){
        return listingService.approveListing(id);
    }

    @GetMapping(path = "/byuser/{id}", produces = "application/json")
    public Collection<Listing> getUserListings(@PathVariable AppUser id) {
        return listingService.getListingByUser(id);
    }
    @GetMapping(path = "/search/{keyword}", produces = "application/json")
    public Collection<Listing> searchListings(@PathVariable String keyword) {
        return listingService.searchListing(keyword);
    }
}
