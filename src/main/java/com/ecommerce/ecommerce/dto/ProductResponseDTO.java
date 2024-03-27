package com.ecommerce.ecommerce.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int remainingQuantity;
    private CategoryDTO categoryDTO;
    private UserResponseDTO userResponseDTO;
}
