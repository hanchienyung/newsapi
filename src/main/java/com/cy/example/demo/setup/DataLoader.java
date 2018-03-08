
package com.cy.example.demo.setup;


import com.cy.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    AppUserRepository appuserRepository;

    @Autowired
    AppRoleRepository approleRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    NewsRepository newsRepository;


    @Override
    public void run(String... strings) throws Exception {
        System.out.println("Loading data...");

        AppRole myUserRole = new AppRole("USER");
        approleRepository.save(myUserRole);

        AppRole myAdminRole = new AppRole("ADMIN");
        approleRepository.save(myAdminRole);


        // Add user roles
        AppUser user1 = new AppUser("burgerb", "password", "Bobby",  "Burger", "bob@burger.com");
        user1.addRole(myUserRole);
        appuserRepository.save(user1);

        AppUser user2 = new AppUser("virginj", "password", "Jane", "Virgin", "jane@virgin.com");
        user2.addRole(myUserRole);
        appuserRepository.save(user2);

        AppUser user3 = new AppUser("smithm", "password", "Mike", "Smith","mike@smith.com");
        user3.addRole(myUserRole);
        appuserRepository.save(user3);

        AppUser user4 = new AppUser("williamsr", "password", "Rod", "Williams", "rod@williams.com");
        user4.addRole(myUserRole);
        appuserRepository.save(user4);

        AppUser user5 = new AppUser("blackl", "password", "Larry", "Black", "larry@black.com");
        user5.addRole(myAdminRole);
        appuserRepository.save(user5);


        //Add User Profiles
        UserProfile profile1 = new UserProfile("profile1", user1);
        userProfileRepository.save(profile1);
        user1.addProfile(profile1);

        appuserRepository.save(user1);

        UserProfile profile2 = new UserProfile("profile2", user2);
        userProfileRepository.save(profile2);
        user2.addProfile(profile2);
        appuserRepository.save(user2);

        UserProfile profile3 = new UserProfile("profile3", user3);
        userProfileRepository.save(profile3);
        user3.addProfile(profile3);
        appuserRepository.save(user3);

        UserProfile profile4 = new UserProfile("profile4", user4);
        userProfileRepository.save(profile4);
        user4.addProfile(profile4);
        appuserRepository.save(user4);

        UserProfile profile5 = new UserProfile("profile5", user5);
        userProfileRepository.save(profile5);
        user5.addProfile(profile5);
        appuserRepository.save(user5);


        //Create news
        News news1 = new News();
        news1.setTitle("Trumps trade war");
        news1.setStory("In his expanding war over ...");
        news1.setCategoryName("worldnews");
        newsRepository.save(news1);

        News news2 = new News();
        news2.setTitle("The crisis next door");
        news2.setStory("The mass exodus of desperate ...");
        news2.setCategoryName("business");
        newsRepository.save(news2);

        News news3 = new News();
        news3.setTitle("Lose now, win later");
        news3.setStory("The NBA standings...");
        news3.setCategoryName("sports");
        newsRepository.save(news3);

        News news4 = new News();
        news4.setTitle("UMBC Leader forms..");
        news4.setStory("The coach had said...");
        news4.setCategoryName("classified");
        newsRepository.save(news4);

        News news5 = new News();
        news5.setTitle("Comedians will be coming early ..");
        news5.setStory("Downtown performance..");
        news5.setCategoryName("travel");
        newsRepository.save(news5);


        //create news categories
        Category category1 = new Category();
        category1.setCategoryName("arts");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setCategoryName("classified");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setCategoryName("travel");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category4.setCategoryName("business");
        categoryRepository.save(category4);

        Category category5 = new Category();
        category5.setCategoryName("worldnews");
        categoryRepository.save(category5);


       //assign categories to userprofiles
       profile1.addCategorytoProfile(category1);
       profile1.addCategorytoProfile(category2);
       userProfileRepository.save(profile1);

       profile2.addCategorytoProfile(category3);
       userProfileRepository.save(profile2);

       profile3.addCategorytoProfile(category1);
       profile3.addCategorytoProfile(category5);
       userProfileRepository.save(profile3);

       profile4.addCategorytoProfile(category4);
       userProfileRepository.save(profile4);

       profile5.addCategorytoProfile(category2);
       userProfileRepository.save(profile5);


    }

}