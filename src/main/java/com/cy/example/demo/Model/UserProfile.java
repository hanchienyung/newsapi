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

    private String topic;

    private String category;


    @ManyToMany
    private Set<AppUser> appusers;


    public void addAppUser(AppUser appUser){

        appusers.add(appUser);

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

    public Set<AppUser> getAppusers() {
        return appusers;
    }

    public void setAppusers(Set<AppUser> appusers) {
        this.appusers = appusers;
    }
}
