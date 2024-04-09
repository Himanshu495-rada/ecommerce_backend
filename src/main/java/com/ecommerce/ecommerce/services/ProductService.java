package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.entities.Category;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.entities.User;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;
import com.ecommerce.ecommerce.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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

    public final String PATH = "C:/Users/himan/Desktop/my_programs/SpringBoot/ecommerce/productImages";

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .toList();
    }

    public ProductResponseDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found"));
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> getAllProductsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with Id " + userId + " not found"));

        List<Product> products = productRepository.findByUser(user);

        return products.stream().map(product -> modelMapper.map(product, ProductResponseDTO.class)).toList();

    }

    public ProductResponseDTO getProductByImage(String productImage) {
        Product product = productRepository.findByImage(productImage);
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public ProductResponseDTO addProduct(ProductDTO productDTO, MultipartFile image) throws IOException {
        User user = userRepository.findById(productDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + productDTO.getUserId() + " not found"));
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category with ID " + productDTO.getCategoryId() + " not found"));

        String fileName = UUID.randomUUID().toString() + image.getOriginalFilename();
        String filePath = PATH + File.separator + fileName;
        image.transferTo(new File(filePath));

        Product product = modelMapper.map(productDTO, Product.class);
        product.setUser(user);
        product.setCategory(category);
        product.setImage(fileName);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    public ProductResponseDTO updateProduct(Long productId, ProductDTO productDTO, MultipartFile image) throws IOException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID " + productId + " not found"));

        productDTO.setProductId(productId); // Ensure ID matches path variable
        modelMapper.map(productDTO, existingProduct); // Update existing object with non-null DTO fields

        if(image != null && !image.isEmpty() && productDTO.getImage() != null){
            String filePath = PATH + File.separator + existingProduct.getImage();
            File existingImage = new File(filePath);
            boolean fileDeletion = existingImage.delete();
            if (!fileDeletion) {
                throw new IOException("Failed to delete image: " + filePath);
            }
            String newFilePath =  PATH + File.separator + UUID.randomUUID().toString() + image.getOriginalFilename();
            image.transferTo(new File(newFilePath));
            productDTO.setImage(newFilePath);
        }else {
            productDTO.setImage(existingProduct.getImage());
        }


        Product savedProduct = productRepository.save(existingProduct);
        return modelMapper.map(savedProduct, ProductResponseDTO.class);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
