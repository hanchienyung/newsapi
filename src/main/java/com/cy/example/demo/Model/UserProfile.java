package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.*;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String topic;

    private String category;


    @ManyToMany
    private List<AppUser> appusers;

    public UserProfile(String topic, String category, List<AppUser> appusers) {
        this.topic = topic;
        this.category = category;
        this.appusers = appusers;
    }


    public void addAppUser(AppUser appUser){

        appusers.add(appUser);
    }

    public UserProfile(){
        appusers = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<AppUser> getAppusers() {
        return appusers;
    }

    public void setAppusers(List<AppUser> appusers) {
        this.appusers = appusers;
    }
}
