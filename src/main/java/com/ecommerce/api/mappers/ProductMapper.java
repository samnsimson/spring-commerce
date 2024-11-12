package com.ecommerce.api.mappers;

import com.ecommerce.api.product.ProductModel;
import com.ecommerce.api.product.dto.ProductInputDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<ProductModel, ProductInputDto>{
    private final ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductInputDto mapTo(ProductModel productModel) {
        return this.modelMapper.map(productModel, ProductInputDto.class);
    }

    @Override
    public ProductModel mapFrom(ProductInputDto productInputDto) {
        return this.modelMapper.map(productInputDto, ProductModel.class);
    }
}
