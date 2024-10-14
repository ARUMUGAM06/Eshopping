package com.eshopping.cart.service.category;

import java.util.List;

import com.eshopping.cart.model.Category;

public interface ICategoryService {
	Category addCategory(Category category);
	Category updateCategory(Category category, int id);
	void deleteCategory(int id);
	Category getCategoryById(int id);
	Category getCategoryByName(String name);
	List<Category> getAllCategory();
}
