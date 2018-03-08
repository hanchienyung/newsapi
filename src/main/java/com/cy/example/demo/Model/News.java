package com.cy.example.demo.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String story;

    private String categoryName;

    @ManyToMany
    private Set<Category> newsCategories;

    public News() {
    }

    public News(String title, String story) {
        this.title = title;
        this.story = story;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Set<Category> getNewsCategories() {
        return newsCategories;
    }

    public void setNewsCategories(Set<Category> newsCategories) {
        this.newsCategories = newsCategories;
    }


}
