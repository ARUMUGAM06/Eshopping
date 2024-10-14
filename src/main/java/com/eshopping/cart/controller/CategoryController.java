package com.eshopping.cart.controller;

import com.eshopping.cart.model.Category;
import com.eshopping.cart.repository.CategoryRepository;
import com.eshopping.cart.response.ApiResponse;
import com.eshopping.cart.service.category.CategoryService;
import com.eshopping.cart.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("${api.prefix}/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/{id}")
    private ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @RequestParam int id){
        Category updatedCategory =categoryService.updateCategory(category, id);
        if(updatedCategory ==null){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error occurred",null));
        }
        return ResponseEntity.ok(new ApiResponse("updated Successfully!.",updatedCategory));
    }

    @GetMapping("/categories")
    private ResponseEntity<ApiResponse> getAllCategories(){
        List<Category> categories=categoryService.getAllCategory();
        if(categories==null){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error occurred",null));
        }
        return ResponseEntity.ok(new ApiResponse("Categories Loaded Successfully!..",categories));
    }

    @PostMapping("/add")
    private ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        Category savedCategory =categoryService.addCategory(category);
        if(savedCategory ==null){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error occurred",null));
        }
        return ResponseEntity.ok(new ApiResponse("Saved Successfully!..",savedCategory));
    }

}
