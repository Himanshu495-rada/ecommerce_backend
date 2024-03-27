package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.dto.ProductRequestDTO;
import com.ecommerce.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Product productRequestDTOToProduct(ProductRequestDTO productRequestDTO ){
        return modelMapper.map(productRequestDTO,Product.class);
    }
    public ProductResponseDTO productToProductResponseDTO(Product product ){
        return modelMapper.map(product,ProductResponseDTO.class);
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(this::productToProductResponseDTO)
                .collect(Collectors.toList());

    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).get();
//                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        ProductResponseDTO productResponseDTO=productToProductResponseDTO(product);
//        product.getCategory().getCategoryId();
//        product.getUser().getId();
        return productResponseDTO;
    }

    public ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) {
        Product product = productRequestDTOToProduct(productRequestDTO);
        Product savedProduct = productRepository.save(product);
        return productToProductResponseDTO(savedProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
//        if (!productRepository.existsById(id)) {
//            throw new ProductNotFoundException("Product not found with id: " + id);
//        }
        productRequestDTO.setId(id); // Ensure the ID is set in the DTO
        Product product =productRequestDTOToProduct(productRequestDTO);
//        Product product = ProductMapper.toEntity(productDTO);
        Product updatedProduct = productRepository.save(product);
        return productToProductResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
//        if (!productRepository.existsById(id)) {
//            throw new ProductNotFoundException("Product not found with id: " + id);
//        }
        productRepository.deleteById(id);
    }

//    public ProductResponseDTO getProductByName(String name) {
//        Product product = productRepository.findByName(name);
////                .orElseThrow(() -> new ProductNotFoundException("Product not found with name: " + name));
//        return productToProductResponseDTO(product);
//    }

//    public List<ProductDTO> getProductByCategory(String category) {
//        List<Product> products = productRepository.findByCategory(category);
//        return ProductMapper.toDTOList(products);
//    }
}

