package com.cy.example.demo.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {
    HashSet <UserProfile> findUserProfileByUsers(AppUser appUser);
   // UserProfile findUserProfileByUser(AppUser appUser);
}