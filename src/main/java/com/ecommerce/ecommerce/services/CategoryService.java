package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.entities.Category;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Implement creation logic
        Category category = new Category();
        category.setName(categoryDTO.getName());
        // Set other properties if needed
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    public CategoryDTO getCategoryById(Long id) {
        // Implement retrieval logic
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return convertToDTO(optionalCategory.get());
        } else {
            // Handle category not found
            return null;
        }
    }

    public List<CategoryDTO> getAllCategories() {
        // Implement retrieval logic
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        // Implement update logic
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setName(categoryDTO.getName());
            // Update other properties if needed
            Category updatedCategory = categoryRepository.save(category);
            return convertToDTO(updatedCategory);
        } else {
            // Handle category not found
            return null;
        }
    }

    public void deleteCategory(Long id) {
        // Implement deletion logic
        categoryRepository.deleteById(id);
    }

    private CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(category.getCategoryId());
        categoryDTO.setName(category.getName());
        // Map other properties as needed
        return categoryDTO;
    }
}

