package com.karuna.pages.listing.model;

import com.karuna.pages.category.model.Category;
import com.karuna.pages.user.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Listing implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "listingname", nullable = false, unique = true)
    private String listingname;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "bannerUrl", nullable = true)
    private String bannerUrl;

    @Column(name = "iconUrl", nullable = true)
    private String iconUrl;

    @Column(name = "approved", nullable = true)
    private int approved;

    @Column(name = "average_rating", nullable = true)
    private int averageRating;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser listinguser;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;

    @Override
    public String toString() {
        return "Listing{" +
                "id=" + id +
                ", listingname='" + listingname + '\'' +
                ", active=" + active +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", user=" + listinguser +
                ", category=" + category +
                '}';
    }
}
