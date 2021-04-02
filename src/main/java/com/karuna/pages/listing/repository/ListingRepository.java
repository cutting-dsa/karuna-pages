package com.karuna.pages.listing.repository;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    Collection<Listing> findAllByCategoryAndActive(Category category, int active);
    Collection<Listing> findAllByActive(int active);

    Listing getListingById(Long id);
}
