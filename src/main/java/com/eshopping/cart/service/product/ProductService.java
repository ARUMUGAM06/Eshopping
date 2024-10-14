package com.eshopping.cart.service.product;

import java.util.List;
import java.util.Optional;
import com.eshopping.cart.exception.ResourceNotSavedException;
import org.springframework.stereotype.Service;
import com.eshopping.cart.exception.ResourceNotFoundException;
import com.eshopping.cart.model.AddProduct;
import com.eshopping.cart.model.Category;
import com.eshopping.cart.model.Product;
import com.eshopping.cart.repository.CategoryRepository;
import com.eshopping.cart.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = ResourceNotSavedException.class)
public class ProductService implements IProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public void addProduct(AddProduct product) {
		try {
			Category category = Optional.ofNullable(categoryRepository
							.findByName(product.getCategory().getName()))
							.orElseGet(() -> {
								Category newCategory = new Category(product.getCategory().getName());
								return categoryRepository.save(newCategory);
							});
			product.setCategory(category);
			productRepository.save(createProduct(product,category));
		}catch(Exception e) {
			throw new ResourceNotSavedException("Product not saved successfully!..");
		}
	}
	
	private Product createProduct(AddProduct product,Category category) {
		return new Product(
				product.getId(),
				product.getName(),
				product.getBrand(),
				product.getInventory(),
				product.getPrice(),
				category
				);
	}
	@Override
	public void updateProduct(Product product, int id) {
		productRepository.findById(id)
				.ifPresentOrElse(productRepository::save,
				()->{throw new ResourceNotFoundException("Product Not Found!..");});
	}

	@Override
	public void deleteProductById(int id) {
		productRepository.findById(id)
						 .ifPresentOrElse(productRepository::delete,
						 () -> {throw new ResourceNotFoundException("Product Not Found!..");});
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product Not Found!.."));
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = productRepository.findAll();
		if(products.isEmpty()) {
			throw new ResourceNotFoundException("No Products saved Yet!..");
		}
		return products;
	}

	@Override
	public List<Product> getProductsByName(String name) {
		List<Product> products=productRepository.findByName(name);
		if(products.isEmpty()) {
			throw new ResourceNotFoundException("No Products Found!..");
		}return products;
	}

	@Override
	public List<Product> getProductsByCategoryName(String category) {
		List<Product> products=productRepository.findByCategoryName(category);
		if(products.isEmpty()) {
			throw new ResourceNotFoundException("No Products Found!..");
		}return products;
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		try {
			return productRepository.findByBrand(brand);
		}catch(Exception e){
			throw new ResourceNotFoundException("No Products Found!..");
		}
	}

	@Override
	public List<Product> getProductsByBrandAndCategoryName(String brand, String category) {
		try {
			return productRepository.findByBrandAndCategoryName(brand, category);
		}catch(Exception e){
			throw new ResourceNotFoundException("No Products Found!..");
		}
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		try {
			return productRepository.findByBrandAndName(brand, name);
		}catch(Exception e){
			throw new ResourceNotFoundException("No Products Found!..");
		}
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		try {
			return productRepository.countByBrandAndName(brand, name);
		}catch(Exception e){
			throw new ResourceNotFoundException("No Products Found!..");
		}
	}

}
