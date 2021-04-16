package com.karuna.pages.reports.utilities;


import com.karuna.pages.category.model.Category;
import com.karuna.pages.listing.model.Listing;
import com.karuna.pages.question.model.Question;
import com.karuna.pages.review.model.Review;
import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
public class ListingReportsTest {

    static List<Listing> listings;

    @BeforeAll
    public static void setUp() {
        listings = stubListing();
    }

    private static List<Role> roles(){
        Role role = new Role(2L,"User");
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);

        return roleList;
    }

    private static List<AppUser> users(){

        List<Role> roleList = roles();

        AppUser appUser1 = new AppUser(1L,"Ruvimbom","Ruvimbo","Ruvimbo","Ruvimbom",1,roleList);

        AppUser appUser2 = new AppUser(1L,"mkalema","Moses","Kalema","password",1,roleList);

        AppUser appUser3 = new AppUser(1L,"dnassolo","Diana","Nassolo","password",1,roleList);

        AppUser appUser4 = new AppUser(1L,"john","John","Joze","password",1,roleList);

        List<AppUser> userList = new ArrayList<>();
        userList.add(appUser1);
        userList.add(appUser2);
        userList.add(appUser3);
        userList.add(appUser4);

        return userList;
    }

    private static List<Listing> stubListing(){

        Category category1 = new Category(1L,"Education",1);
        Role role = new Role(2L,"User");
        List<Role> roleList = roles();

        AppUser appUser1 = users().get(0);
        AppUser appUser4 = users().get(3);
        Listing listing1 = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png",
                "icon.png",0,1,appUser1,category1,null);
        listing1.setReviewList(stubReview(listing1, appUser4));
        listing1.getReviewList().get(0).setRating(5);
        listing1.getReviewList().get(1).setRating(3);
        listing1.getReviewList().get(2).setRating(2);

        AppUser appUser2 = users().get(1);
        AppUser appUser3 = users().get(2);
        Listing listing2 = new Listing(1L,"Jefferson",1,"someAddress",123.2,321.1,"banner.png","icon.png",0,1,appUser2,category1,null);
        listing2.setReviewList(stubReview(listing2, appUser3));
        listing2.getReviewList().get(0).setRating(1);
        listing2.getReviewList().get(1).setRating(2);
        listing2.getReviewList().get(2).setRating(3);

        return Arrays.asList(listing1, listing2);

    }

    private static List<Review> stubReview(Listing listing, AppUser user){

        Review review1 = new Review(1L,"This is my comment",4,1, user,listing);
        Review review2 = new Review(1L,"This is my comment",2,1, user,listing);
        Review review3 = new Review(1L,"This is my comment",3,1, user,listing);

        return Arrays.asList(review1, review2, review3);

    }

    private static List<Category> stubCategories(){
        Category category1 = new Category(1L,"Education",1);
        Category category2 = new Category(1L,"Retail",1);
        Category category3 = new Category(1L,"Food",1);
        Category category4 = new Category(1L,"Hotels",1);

        return Arrays.asList(category1, category2, category3, category4);
    }

    private static List<Question> stubQuestions(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021,4,15,10,59,59);
        Date qDate = calendar.getTime();


        Question question1 = new Question(1L,"Which programs are offered at Maharishi",true,stubCategories().get(0), users().get(3), null, qDate);
        Question question2 = new Question(1L,"What is the population of Maharishi International University",true,stubCategories().get(0), users().get(4), null, qDate);
        Question question3 = new Question(1L,"How good is the food at Argiro",true,stubCategories().get(2), users().get(4), null, qDate);
        Question question4 = new Question(1L,"What are the best Hotels in Fairfield",true,stubCategories().get(3), users().get(1), null, qDate);
        Question question5 = new Question(1L,"Is there Walmart in Iowa",true,stubCategories().get(1), users().get(2), null, qDate);

        return Arrays.asList(question1, question2, question3, question4, question5);
    }

    @Test
    public void testTOP_K_USER_WITH_BEST_RATED_LISTIN1(){

        List<AppUser> users = ListingReports.TOP_K_USER_WITH_BEST_RATED_LISTING.apply(listings, 2L);

        Assert.assertEquals(2, users.size());

    }

    @Test
    public void testTOP_K_USER_WITH_BEST_RATED_LISTING2(){

        List<AppUser> users = ListingReports.TOP_K_USER_WITH_BEST_RATED_LISTING.apply(listings, 1L);

        AppUser expectedUser = users().get(0);

        Assert.assertEquals(1, users.size());
        Assert.assertEquals(expectedUser.getFirstName(), users.get(0).getFirstName());

    }

    @Test
    public void testUSERS_WHO_GAVE_LEAST_AVERAGE_RATING_ON_LISTINGS1(){

        List<AppUser> users = ListingReports.USERS_WHO_GAVE_LEAST_AVERAGE_RATING_ON_LISTINGS.apply(listings, 2L);

        Assert.assertEquals(2, users.size());

        List<String> userFirstNames = users.stream().map(u -> u.getFirstName()).collect(Collectors.toList());

        assertThat(userFirstNames, containsInAnyOrder("Diana", "John"));

    }

    @Test
    public void testUSERS_WHO_GAVE_LEAST_AVERAGE_RATING_ON_LISTINGS2(){

        List<AppUser> users = ListingReports.USERS_WHO_GAVE_LEAST_AVERAGE_RATING_ON_LISTINGS.apply(listings, 1L);

        AppUser expectedUser = users().get(2);

        Assert.assertEquals(1, users.size());
        Assert.assertEquals(expectedUser.getFirstName(), users.get(0).getFirstName());

    }
}
