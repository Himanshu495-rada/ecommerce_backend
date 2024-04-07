package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.dto.ProductDTO;
import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.services.ProductService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ProductService productService;

    public final String PRODUCT_PATH = "C:/Users/himan/Desktop/my_programs/SpringBoot/ecommerce/productImages";
    public final String CATEGORY_PATH = "C:/Users/himan/Desktop/my_programs/SpringBoot/ecommerce/categoryImages";

    @GetMapping("/product/{productImage}")
    public ResponseEntity<UrlResource> getProductImage(@PathVariable String productImage) throws IOException {

        String filePath = PRODUCT_PATH + File.separator + productImage;
        File imageFile = new File(filePath);
        UrlResource resource = new UrlResource(imageFile.toURI());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @GetMapping("/category/{categoryImage}")
    public ResponseEntity<UrlResource> getCategoryImage(@PathVariable String categoryImage) throws IOException {
        String filePath = CATEGORY_PATH + File.separator + categoryImage;
        File imageFile = new File(filePath);
        UrlResource resource = new UrlResource(imageFile.toURI());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return ResponseEntity.ok().headers(headers).body(resource);
    }

}
