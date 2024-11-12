package com.ecommerce.api.product;

import com.ecommerce.api.__constructs.CrudController;
import com.ecommerce.api.product.dto.ProductInputDto;
import com.ecommerce.api.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController implements CrudController<ProductModel, ProductInputDto, String> {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    @PostMapping
    public ApiResponse<ProductModel> create(@Valid @RequestBody ProductInputDto entity) {
        ProductModel product = this.productService.create(entity);
        return new ApiResponse<>(HttpStatus.CREATED, product);
    }

    @Override
    @GetMapping("{id}")
    public ApiResponse<ProductModel> getById(@PathVariable String id) {
        ProductModel product = this.productService.getById(id);
        return new ApiResponse<>(HttpStatus.OK, product);
    }

    @Override
    @GetMapping
    public ApiResponse<List<ProductModel>> getAll() {
        List<ProductModel> products = this.productService.getAll();
        return new ApiResponse<>(HttpStatus.OK, products);
    }

    @Override
    @PutMapping("{id}")
    public ApiResponse<ProductModel> update(@PathVariable String id, @Valid @RequestBody ProductInputDto entity) {
        ProductModel product = this.productService.update(id, entity);
        return new ApiResponse<>(HttpStatus.OK, product);
    }

    @Override
    @DeleteMapping("{id}")
    public ApiResponse<Boolean> delete(@PathVariable String id) {
        boolean deleted = this.productService.delete(id);
        return new ApiResponse<>(HttpStatus.OK, deleted);
    }
}
