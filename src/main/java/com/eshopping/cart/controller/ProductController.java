package com.eshopping.cart.controller;

import com.eshopping.cart.model.AddProduct;
import com.eshopping.cart.model.Product;
import com.eshopping.cart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/getAll")
    private List<Product> getAllproducts(){
        return productService.getAllProducts();
    }

    @PostMapping("/save")
    private void saveProduct(@RequestBody AddProduct product){
        productService.addProduct(product);
    }
}
