package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.entities.Category;
import com.ecommerce.ecommerce.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //get all categories
    @GetMapping  //http://localhost:8083/api/category
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //add category
    @PostMapping //http://localhost:8083/api/category
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> addCategory(@ModelAttribute CategoryDTO categoryDTO, @RequestParam("imageFile") MultipartFile image) throws IOException {
        CategoryDTO savedCategoryDTO = categoryService.addCategory(categoryDTO, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDTO);
    }

    //edit category
    @PutMapping("/{categoryId}") //http://localhost:8083/api/category/1
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setCategoryId(categoryId); // Ensure ID matches path variable
        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryId, categoryDTO);
        return ResponseEntity.ok(updatedCategoryDTO);
    }

    //delete category
    @DeleteMapping("/{categoryId}") //http://localhost:8083/api/category/1
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
