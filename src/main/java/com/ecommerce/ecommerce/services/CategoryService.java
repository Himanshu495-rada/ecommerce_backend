package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.CategoryDTO;
import com.ecommerce.ecommerce.entities.Category;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public final String PATH = "C:/Users/himan/Desktop/my_programs/SpringBoot/ecommerce/images";

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO, MultipartFile image) throws IOException {
        Category category = modelMapper.map(categoryDTO, Category.class);

        String fileName = UUID.randomUUID().toString() + image.getOriginalFilename();
        String filePath = PATH + File.separator + fileName;
        image.transferTo(new File(filePath));

        category.setImage(fileName);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("Category with ID " + categoryId + " not found"));
        modelMapper.map(categoryDTO, existingCategory); // Update existing object
        Category savedCategory = categoryRepository.save(existingCategory);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

}
