package com.eshopping.cart.service.product;

import java.util.List;
import com.eshopping.cart.model.AddProduct;
import com.eshopping.cart.model.Product;

public interface IProductService {
	Product addProduct(AddProduct product);
	Product updateProduct(Product product,int id);
	void deleteProductById(int id);
	Product getProductById(int id);
	List<Product> getAllProducts();
	List<Product> getProductsByName(String name);
	List<Product> getProductsByCategoryName(String category);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductsByBrandAndCategoryName(String brand, String category);
	List<Product> getProductsByBrandAndName(String brand,String name);
	Long countProductsByBrandAndName(String brand,String name); 
}
