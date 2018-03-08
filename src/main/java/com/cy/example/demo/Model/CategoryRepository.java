package com.cy.example.demo.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface CategoryRepository extends CrudRepository<Category, Long> {
  //  HashSet<Category> findCategoriesByUser(AppUser appUser);
}
