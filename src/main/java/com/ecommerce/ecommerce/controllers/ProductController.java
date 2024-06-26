package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.dto.ProductResponseDTO;
import com.ecommerce.ecommerce.services.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long productId) {
        ProductResponseDTO productResponseDTO = productService.getProduct(productId);
        return ResponseEntity.ok(productResponseDTO);
    }

    @GetMapping("/seller/{userId}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<ProductResponseDTO>> getAllProductsByUser(@PathVariable Long userId) {
        List<ProductResponseDTO> products = productService.getAllProductsByUser(userId);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<ProductResponseDTO> addProduct(@ModelAttribute ProductDTO productDTO, @RequestParam("imageFile") MultipartFile image) throws IOException {
        ProductResponseDTO savedProductDTO = productService.addProduct(productDTO, image);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProductDTO);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long productId, @ModelAttribute ProductDTO productDTO, @RequestParam(value = "imageFile", required = false) MultipartFile image) throws IOException {
        productDTO.setProductId(productId);
        ProductResponseDTO updatedProductDTO = productService.updateProduct(productId, productDTO, image);
        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
