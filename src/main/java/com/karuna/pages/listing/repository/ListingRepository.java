package com.karuna.pages.listing.repository;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    Collection<Listing> findAllByCategoryAndActive(Category category, int active);
    List<Listing> findAllByActive(int active);
    Collection<Listing> findAllByListinguser(AppUser id);

    Listing getListingById(Long id);

    Collection<Listing> findListingByListingname(String name);


    @Query("SELECT l FROM Listing l WHERE l.listingname LIKE %:keyword%")
    Collection<Listing> searchListing(@Param("keyword")String keyword);
}
