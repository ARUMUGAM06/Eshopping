package com.eshopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.cart.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Category findByName(String name);
}
