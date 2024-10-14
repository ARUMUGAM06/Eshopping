package com.eshopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eshopping.cart.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	List<Product> findByCategoryName(String name);
	List<Product> findByBrand(String brand);
	List<Product> findByBrandAndCategoryName(String brand,String category);
	List<Product> findByBrandAndName(String brand,String name);
	List<Product> findByName(String name);
	Long countByBrandAndName(String brand,String name);
}
