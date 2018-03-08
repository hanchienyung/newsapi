package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String categoryName;

    @ManyToMany
    private Set<UserProfile> categoryProfiles;

    @ManyToMany
    private Set<News> categoryNews;

    public Category() {
        this.categoryProfiles = new HashSet<>();
        this.categoryNews = new HashSet<>();
    }

    public Category(String categoryName)
    {
        this.categoryName = categoryName;
        this.categoryProfiles = new HashSet<>();
        this.categoryNews = new HashSet<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<UserProfile> getCategoryProfiles() {
        return categoryProfiles;
    }

    public void setCategoryProfiles(Set<UserProfile> categoryProfiles) {
        this.categoryProfiles = categoryProfiles;
    }

    public Set<News> getCategoryNews() {
        return categoryNews;
    }

    public void setCategoryNews(Set<News> categoryNews) {
        this.categoryNews = categoryNews;
    }
}
