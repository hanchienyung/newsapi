package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserProfile {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(unique=true)
        private String profileName;


        @OneToOne //(mappedBy = "userProfile",fetch = FetchType.LAZY)
        private AppUser user;

        @ManyToMany
        private Set<Category> profileCategories;


        public UserProfile() {
           this.user = new AppUser();
        }

        public UserProfile(String profileName, AppUser user) {
        this.profileName = profileName;
        this.user = user;

       }

       public  void addCategorytoProfile(Category acategory) {
            this.profileCategories.add(acategory);

       }


        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getProfileName() {
            return profileName;
        }

        public void setProfileName(String profileName) {
            this.profileName = profileName;
        }

        public AppUser getUser() {
            return user;
        }

        public void setUser(AppUser user) {
            this.user = user;
        }

        public Set<Category> getProfileCategories() {
            return profileCategories;
        }

        public void setProfileCategories(Set<Category> profileCategories) {
            this.profileCategories = profileCategories;
        }
    }
