package com.eshopping.cart.controller;

import com.eshopping.cart.model.Category;
import com.eshopping.cart.response.ApiResponse;
import com.eshopping.cart.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/{id}")
    private ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @RequestParam int id){
        return ResponseEntity.ok(new ApiResponse("updated Successfully!.",categoryService.updateCategory(category, id)));
    }

    @GetMapping("/categories")
    private ResponseEntity<ApiResponse> getAllCategories(){;
        return ResponseEntity.ok(new ApiResponse("Categories Loaded Successfully!..",categoryService.getAllCategory()));
    }

    @PostMapping("/add")
    private ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(new ApiResponse("Saved Successfully!..",categoryService.addCategory(category)));
    }
}
