package com.karuna.pages.review.model;

import com.karuna.pages.core.exceptions.BadRequestException;
import com.karuna.pages.core.exceptions.UnsupportedTypeException;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.user.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.sound.sampled.ReverbType;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment", nullable = false, unique = true)
    private String comment;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "status", nullable = false)
    private Integer status;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser reviewUser;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "listing_id", referencedColumnName = "id")
    private Listing listing;


    public void editReview(Review review){
        if(review.comment != null) this.setComment(review.comment);

        if( !(review.rating >= 0 && review.rating <=5)) throw new UnsupportedTypeException("Rating should be between 1 and 5");

         this.setRating(review.rating);

        if( review.reviewUser.getId() != this.reviewUser.getId()) throw new BadRequestException("Can not change review user");
        if( review.listing.getId() != this.listing.getId()) throw new BadRequestException("Can not change review listing");

    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", reviewUser=" + reviewUser +
                ", listing=" + listing +
                '}';
    }
}
