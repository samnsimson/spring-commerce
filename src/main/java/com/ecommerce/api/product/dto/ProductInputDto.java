package com.ecommerce.api.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductInputDto {
    @NotNull(message = "Name of the product is required")
    @Size(min = 3, max = 50, message = "Length of the name must be between 3 & 50 characters")
    private String name;

    private String description = null;

    @NotNull(message = "Retail price cannot be empty")
    @DecimalMin(value = "0.0", inclusive = false, message = "Retail price must be greater than 0")
    private Float retailPrice;

    @DecimalMin(value = "0.0", message = "Sale price must be at least 0")
    private Float salePrice;
}
