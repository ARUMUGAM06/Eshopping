package com.eshopping.cart.controller;

import com.eshopping.cart.model.AddProduct;
import com.eshopping.cart.model.Product;
import com.eshopping.cart.response.ApiResponse;
import com.eshopping.cart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAll")
    private ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> product = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Products Loaded Successfully",product));
    }

    @PostMapping("/save")
    private ResponseEntity<ApiResponse> saveProduct(@RequestBody AddProduct product){
        Product savedProduct= productService.addProduct(product);
        return ResponseEntity.ok(new ApiResponse("Product Saved Successfully!..",savedProduct));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<ApiResponse> deleteProduct(@PathVariable int id){
        productService.deleteProductById(id);
        return ResponseEntity.ok(new ApiResponse("Deleted Successfully",null));
    }
    @GetMapping("/brandAndName/")
    private ResponseEntity<ApiResponse> getProductsByBrandAndName(@RequestParam String brand,@RequestParam String name){
        return ResponseEntity
                .ok(new ApiResponse("Products Loaded Successfully",productService.getProductsByBrandAndName(brand,name)));
    }

}
