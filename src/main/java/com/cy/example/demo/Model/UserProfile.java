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

    //private String category;


    @ManyToMany //(mappedBy = "userprofiles")
    private Set<AppUser> users;

   // @OneToOne
   // private AppUser user;


    //@ManyToMany //(mappedBy = "categories")
    //private Set<Category> profileCategories;


    public UserProfile() {
        this.users = new HashSet<>();
       // this.user = new AppUser();
        //this.profileCategories = new HashSet<>();
    }

    public UserProfile(String topic) { //}, AppUser appuser) {
        this.topic = topic;
        this.users = new HashSet<>();
       // this.user = user;

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

  /*  public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    */


    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }


      /* public  void addCategorytoProfile(Category acategory) {
            this.profileCategories.add(acategory);

       }*/

}
