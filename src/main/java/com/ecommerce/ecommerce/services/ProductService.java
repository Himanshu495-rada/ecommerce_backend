package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entities.Category;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public ProductDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        User user = userRepository.findById(productDTO.getUser().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + productDTO.getUser().getUserId() + " not found"));
        Category category = categoryRepository.findById(productDTO.getCategory().getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + productDTO.getCategory().getCategoryId() + " not found"));

        Product product = modelMapper.map(productDTO, Product.class);
        product.setUser(user);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found"));

        productDTO.setProductId(productId); // Ensure ID matches path variable

        modelMapper.map(productDTO, existingProduct); // Update existing object with non-null DTO fields
        Product savedProduct = productRepository.save(existingProduct);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
