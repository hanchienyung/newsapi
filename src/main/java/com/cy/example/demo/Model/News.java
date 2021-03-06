package com.cy.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News {

    private String status;
    private int totalResults;
    private ArrayList<Article> articles;


    public String getStatus() { return this.status; }
    public void setStatus(String status) { this.status = status; }


    public int getTotalResults() { return this.totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }


    public ArrayList<Article> getArticles() { return this.articles; }
    public void setArticles(ArrayList<Article> articles) { this.articles = articles; }


}
