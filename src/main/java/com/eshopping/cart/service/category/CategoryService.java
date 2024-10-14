package com.eshopping.cart.service.category;

import java.util.List;

import com.eshopping.cart.exception.ResourceNotFoundException;
import com.eshopping.cart.exception.ResourceNotSavedException;
import com.eshopping.cart.service.product.IProductService;
import org.springframework.stereotype.Service;

import com.eshopping.cart.model.Category;
import com.eshopping.cart.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = ResourceNotSavedException.class)
public class CategoryService implements ICategoryService{

	private final CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		try {
			return categoryRepository.save(category);
		}catch(Exception e) {
			throw new ResourceNotSavedException("Category not be saved successfully!..");
		}
	}

	@Override
	public Category updateCategory(Category category, int id) {
		  return categoryRepository.findById(id).map(existingCategory->{
			  existingCategory.setName(category.getName());
			  return categoryRepository.save(existingCategory);
		  }).orElseThrow(()->new ResourceNotFoundException("Category not found!.."));
	}

	@Override
	public void deleteCategory(int id) {
		categoryRepository.findById(id).ifPresentOrElse(
									categoryRepository::delete, 
									()->{throw new ResourceNotFoundException("Category not found!...");});
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Category not found!..."));
	}

	@Override
	public Category getCategoryByName(String name) {
		Category category = categoryRepository.findByName(name);
		if(category == null) {
			throw new ResourceNotFoundException("Category not found!...");
		}
		return category;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		if (categories.isEmpty()) {
			throw new ResourceNotFoundException("No Categories saved yet!...");
		}return categories;
	}
}
