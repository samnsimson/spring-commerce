package com.ecommerce.api.product;

import com.ecommerce.api.__constructs.CrudService;
import com.ecommerce.api.mappers.ProductMapper;
import com.ecommerce.api.product.dto.ProductInputDto;
import com.ecommerce.api.utils.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements CrudService<ProductModel, ProductInputDto, String> {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductModel create(ProductInputDto entity) {
        ProductModel product = this.productMapper.mapFrom(entity);
        return this.productRepository.save(product);
    }

    @Override
    public ProductModel getById(String id) {
        Optional<ProductModel> product = this.productRepository.findById(id);
        if(product.isEmpty()) throw new ResourceNotFoundException("Product not found");
        return product.get();
    }

    @Override
    public List<ProductModel> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public ProductModel update(String id, ProductInputDto entity) {
        boolean productExists = this.productRepository.existsById(id);
        if(!productExists) throw new ResourceNotFoundException("Product not found");
        ProductModel product = this.productMapper.mapFrom(entity);
        product.setId(id);
        return this.productRepository.save(product);
    }

    @Override
    public boolean delete(String id) {
        boolean productExists = this.productRepository.existsById(id);
        if(!productExists) throw new ResourceNotFoundException("Product not found");
        this.productRepository.deleteById(id);
        return true;
    }
}
